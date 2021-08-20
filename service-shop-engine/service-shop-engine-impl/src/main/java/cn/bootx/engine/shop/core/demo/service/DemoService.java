package cn.bootx.engine.shop.core.demo.service;

import cn.bootx.common.util.BigDecimalUtil;
import cn.bootx.engine.shop.core.order.dao.OrderCacheManager;
import cn.bootx.engine.shop.core.pay.service.PayModeService;
import cn.bootx.engine.shop.mq.MessageSender;
import cn.bootx.engine.shop.param.demo.DemoPayParam;
import cn.bootx.engine.shop.param.demo.DemoPlaceAndPayParam;
import cn.bootx.engine.shop.param.demo.DemoPlaceOrderParam;
import cn.bootx.engine.shop.param.sell.OrderPayParam;
import cn.bootx.goods.client.GoodsSkuClient;
import cn.bootx.goods.client.InventoryClient;
import cn.bootx.goods.dto.inventory.LockInventoryDto;
import cn.bootx.goods.dto.sku.GoodsSkuDto;
import cn.bootx.goods.exception.inventory.InventoryInsufficientException;
import cn.bootx.goods.param.inventory.ReduceInventoryParam;
import cn.bootx.order.client.OrderOperateClient;
import cn.bootx.order.code.OrderStatusCode;
import cn.bootx.order.dto.order.OrderDetailDto;
import cn.bootx.order.dto.order.OrderDto;
import cn.bootx.order.param.order.OrderDetailParam;
import cn.bootx.order.param.order.OrderParam;
import cn.bootx.order.param.order.OrderWholeParam;
import cn.bootx.paymentcenter.client.PayClient;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import cn.bootx.paymentcenter.dto.paymodel.wallet.WalletDto;
import cn.bootx.paymentcenter.param.pay.PayParam;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 结算台demo
 * @author xxm
 * @date 2021/7/23
 */
@SuppressWarnings("FieldCanBeLocal")
@Slf4j
@Service
@RequiredArgsConstructor
public class DemoService {
    private final GoodsSkuClient goodsSkuClient;
    private final InventoryClient inventoryClient;
    private final OrderOperateClient orderOperateClient;
    private final PayClient payClient;

    private final MessageSender messageSender;
    private final OrderCacheManager orderCacheManager;
    private final PayModeService payModeService;

    private final Long USER_ID = 1L;
    private final String MERCHANT_NO = "M1410587445766025216";
    private final String APP_ID = "1368825551321722880";

    /**
     * 商品列表接口
     */
    public List<GoodsSkuDto> findGoods(Long goodsId){
        return goodsSkuClient.findByGoodsId(goodsId);
    }

    /**
     * 下单和支付发起方法
     */
    public PayResult placeAndPay(DemoPlaceAndPayParam param){
        // 订单信息  支付方式信息

        // 下单
        List<DemoPlaceOrderParam> placeOrder = param.getPlaceOrder();
        Map<Long, Integer> skuMaps = placeOrder.stream().collect(Collectors.toMap(DemoPlaceOrderParam::getSkuId, DemoPlaceOrderParam::getCount));

        List<GoodsSkuDto> skus = goodsSkuClient.findBySkuIds(new ArrayList<>(skuMaps.keySet()));
        List<OrderDetailParam> orderDetailParams = skus.stream().map(sku -> {
            OrderDetailParam od = new OrderDetailParam();
            BeanUtil.copyProperties(sku, od);
            return od.setNum(skuMaps.get(sku.getId()))
                    .setGoodsPrice(sku.getPrice())
                    .setGoodsId(sku.getGoodsId())
                    .setSkuId(sku.getId())
                    .setCategoryId(sku.getCid())
                    .setGoodsTitle(sku.getName());
        }).collect(Collectors.toList());
        OrderDto order = this.createOrder(orderDetailParams);
        order.setDescription("测试支付");

        // 发起支付
        return this.payOrder(order, param.getPayParam());
    }

    /**
     * 获取钱包
     */
    public WalletDto findWallet(){
        return null;
    }

    /**
     * 支付记录列表
     */
    public void pagePayment(){

    }

    /**
     * 获取支付记录
     */
    public void findPayment(Long id){

    }

    /**
     * 获取账单Billing
     */
    public void findBilling(String businessId){

    }


    /**
     * 支付
     */
    public PayResult payOrder(OrderDto order, DemoPayParam param){

        // 构建支付参数
        OrderPayParam orderPayParam = new OrderPayParam()
                .setPayModeList(param.getPayModeList())
                .setOrderId(order.getId());
        PayParam payParam = payModeService.buildPaymentParam(order,orderPayParam);
        payParam.setMerchantNo(MERCHANT_NO)
                .setAppId(APP_ID);
        // 发起支付
        PayResult payResult = payClient.pay(payParam);

        // 异步支付
        if (payResult.isSyncPayMode()){
            return payResult;
        } else {
            // 同步支付后处理
            this.afterPaymentHandler(order);
        }
        return payResult;
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

    /**
     * 创建订单
     */
    public OrderDto createOrder(List<OrderDetailParam> orderDetailParams){
        // 构建下单参数
        OrderParam orderParam = this.buildOrderParam(orderDetailParams);

        // 校验库存和锁定库存
        this.checkInventory(orderDetailParams);
        Map<Long, LockInventoryDto> inventoryMap = this.lockInventory(orderDetailParams);
        OrderWholeParam orderWholeParam = new OrderWholeParam()
                .setOrderParam(orderParam);

        // 下单
        OrderDto order = orderOperateClient.placeOrder(orderWholeParam);

        // 预占库存token缓存
        inventoryMap.forEach((skuId, lockInventory) -> {
            //将token写入redis
            orderCacheManager.saveInventoryLockToken(order.getId(), skuId, lockInventory.getToken());
        });
        // 保存订单超时时间
        orderCacheManager.storeByOrderId(order.getId());
        return order;
    }

    /**
     * 构建订单参数
     */
    private OrderParam buildOrderParam(List<OrderDetailParam> orderDetailParams) {

        // 订单
        OrderParam orderParam = new OrderParam();

        BigDecimal totalAmount = orderDetailParams.stream()
                .map(detail -> BigDecimalUtil.multiply(detail.getGoodsPrice(), BigDecimal.valueOf(detail.getNum())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        orderParam.setUserId(USER_ID);
        orderParam.setType(1);
        orderParam.setTotalAmount(totalAmount);
        orderParam.setDescription("测试支付");
        orderParam.setDetails(orderDetailParams);
        return orderParam;
    }

    /**
     * 检查库存
     */
    private void checkInventory(List<OrderDetailParam> detailParams) {

        for (OrderDetailParam orderDetail : detailParams) {
            int inventory = Optional.ofNullable(inventoryClient.getAvailable(orderDetail.getSkuId())).orElse(0);

            if (inventory < orderDetail.getNum()) {
                throw new InventoryInsufficientException();
            }
        }
    }

    /**
     * 锁定库存
     */
    private Map<Long,LockInventoryDto> lockInventory(List<OrderDetailParam> orderDetailParams) {
        Map<Long,LockInventoryDto> map = new LinkedHashMap<>();
        for (OrderDetailParam detailParam : orderDetailParams) {
            LockInventoryDto inventory = inventoryClient.lockInventory(detailParam.getSkuId(), detailParam.getNum());
            map.put(detailParam.getSkuId(),inventory);
        }
        return map;
    }
}
