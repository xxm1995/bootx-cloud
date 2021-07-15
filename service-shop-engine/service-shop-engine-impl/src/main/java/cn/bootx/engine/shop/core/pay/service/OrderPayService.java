package cn.bootx.engine.shop.core.pay.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.engine.shop.core.order.dao.OrderCacheManager;
import cn.bootx.engine.shop.mq.MessageSender;
import cn.bootx.engine.shop.param.sell.OrderPayParam;
import cn.bootx.goodscenter.client.GoodsSkuClient;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.inventory.ReduceInventoryParam;
import cn.bootx.ordercenter.client.OrderFindClient;
import cn.bootx.ordercenter.client.OrderOperateClient;
import cn.bootx.ordercenter.code.OrderStatusCode;
import cn.bootx.ordercenter.dto.order.OrderDetailDto;
import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.bootx.ordercenter.exception.order.OrderTimeOutException;
import cn.bootx.paymentcenter.client.PayClient;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import cn.bootx.paymentcenter.param.pay.PayParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 支付服务
 * @author xxm
 * @date 2020/12/11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderPayService {

    private final OrderCacheManager orderCacheManager;
    private final PayModeService payModeService;

    private final OrderFindClient orderFindClient;
    private final OrderOperateClient orderOperateClient;
    private final PayClient payClient;
    private final GoodsSkuClient goodsSkuClient;

    private final MessageSender messageSender;

    private final String MERCHANT_NO = "M1410587445766025216";
    private final String APP_ID = "1368825551321722880";

    /**
     * 支付
     */
    public PayResult payOrder(OrderPayParam param){
        // 支付前处理 获取订单,校验操作等
        OrderDto orderDto = this.prePaymentHandler(param);

        // 构建支付参数
        PayParam payParam = payModeService.buildPaymentParam(orderDto,param);
        payParam.setMerchantNo(MERCHANT_NO)
                .setAppId(APP_ID);
        // 发起支付
        PayResult payResult = payClient.pay(payParam);

        // 异步支付
        if (payResult.isSyncPayMode()){
            return payResult;
        } else {
            // 同步支付后处理
            this.afterPaymentHandler(orderDto);
        }
        return payResult;
    }

    /**
     * 支付前处理
     *
     * @param param 支付参数
     */
    private OrderDto prePaymentHandler(OrderPayParam param) {
        // 获取订单信息
        OrderDto orderDto = orderFindClient.getWholeById(param.getOrderId());

        // 状态检查
        switch (orderDto.getStatus()) {
            case OrderStatusCode.STATUS_NOT_YET_SHIPPED:
            case OrderStatusCode.STATUS_PAYED:
                throw new BizException("订单已完成");
            case OrderStatusCode.STATUS_CANCEL:
                throw new BizException("订单已经取消");
            default:
        }

        //校验lock库存是否有效，防止因服务器调整时间造成的缓存问题
        List<OrderDetailDto> detailsDtoList = orderDto.getDetails();
        Map<Long, List<OrderDetailDto>> skuGroupOrderDetailMap = detailsDtoList.stream()
                .collect(Collectors.groupingBy(OrderDetailDto::getSkuId));
        // 扣库存
        skuGroupOrderDetailMap.forEach((skuId, detailDtoList) -> {
            //所有支付，订单有效的情况下都可以扣库存
            GoodsSkuDto sku = goodsSkuClient.getById(skuId);
            int num = detailDtoList.stream()
                    .mapToInt(OrderDetailDto::getNum)
                    .sum();
            if (num > sku.getLocked()) {
                throw new OrderTimeOutException();
            }
        });
        return orderDto;
    }

    /**
     * 支付成功后操作
     */
    public void afterPaymentHandler(OrderDto orderDto) {

        Map<Long, List<OrderDetailDto>> skuGroupOrderDetailMap = orderDto.getDetails().stream()
                .collect(Collectors.groupingBy(OrderDetailDto::getSkuId));
        skuGroupOrderDetailMap.forEach((skuId, detailDtoList) -> {
            String token = orderCacheManager.getSkuLockToken(orderDto.getId(), skuId);
            int sum = detailDtoList.stream().mapToInt(OrderDetailDto::getNum).sum();
            ReduceInventoryParam reduceInventoryParam = new ReduceInventoryParam()
                    .setSkuId(skuId)
                    .setCount(sum)
                    .setToken(token);
            // 扣减已锁定库存
            messageSender.sendReduceInventory(reduceInventoryParam);
            // 删除库存锁定token信息
            orderCacheManager.deleteSkuLockToken(orderDto.getId(), skuId);
        });
        // 更新订单支付已完成
        orderDto.setStatus(OrderStatusCode.STATUS_PAYED);
        // 删除订单过期时间信息
        orderCacheManager.removeExpiredOrder(orderDto.getId());
        orderOperateClient.paidOrderState(orderDto.getId());
    }
}
