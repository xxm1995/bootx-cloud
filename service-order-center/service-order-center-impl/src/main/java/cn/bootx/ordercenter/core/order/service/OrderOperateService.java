package cn.bootx.ordercenter.core.order.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.ordercenter.code.OrderStatusCode;
import cn.bootx.ordercenter.core.order.dao.*;
import cn.bootx.ordercenter.core.order.entity.Order;
import cn.bootx.ordercenter.core.order.entity.OrderDetail;
import cn.bootx.ordercenter.core.order.entity.OrderStrategyMapping;
import cn.bootx.ordercenter.core.order.entity.OrderWhole;
import cn.bootx.ordercenter.core.order.factory.OrderFactory;
import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.bootx.ordercenter.param.order.OrderParam;
import cn.bootx.ordercenter.param.order.OrderWholeParam;
import cn.bootx.ordercenter.transform.SalesCenterTransform;
import cn.bootx.salescenter.client.CouponClient;
import cn.bootx.salescenter.client.OrderPreviewClient;
import cn.bootx.salescenter.dto.coupon.CouponDto;
import cn.bootx.salescenter.dto.order.OrderPreviewResult;
import cn.bootx.salescenter.param.order.OrderCheckParam;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 订单构建操作类
 * @author xxm
 * @date 2020/10/12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderOperateService {
    private final OrderRepository orderRepository;
    private final OrderWholeRepository orderWholeRepository;
    private final OrderManager orderManager;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderStrategyMappingRepository orderStrategyMappingRepository;

    private final OrderFactory orderFactory;
    private final SalesCenterTransform salesCenterTransform;
    private final OrderPreviewClient orderPreviewClient;
    private final CouponClient couponClient;

    /**
     * 传入订单和优惠, 下单
     */
    @Transactional(rollbackFor = Exception.class)
    public OrderDto placeOrder(OrderWholeParam orderWholeParam){

        OrderParam orderParam = orderWholeParam.getOrderParam();
        OrderCheckParam orderCheckParam = salesCenterTransform.buildCheckOrder(orderParam);

        // 计算价格
        OrderPreviewResult orderCheck = orderPreviewClient.previewOrderPrice(orderCheckParam);

        // 转换成订单对象
        OrderWhole order = orderFactory.buildOrder(orderCheck,orderWholeParam.getOrderParam());
        order.setAddressInfo(orderWholeParam.getOrderAddressInfo());
        order.setInvoiceInfo(orderWholeParam.getOrderInvoiceInfo());

        // 锁定优惠券
        if (CollUtil.isNotEmpty(orderCheck.getCoupons())){
            List<Long> couponIds = orderCheck.getCoupons().stream().map(CouponDto::getId).collect(Collectors.toList());
            couponClient.lockByIds(couponIds);
        }

        // 订单持久化
        this.orderInfoPersistence(order);
        // 发布订单创建成功事件

        return order.toDto();
    }

    /**
     * 持久化
     */
    private void orderInfoPersistence(OrderWhole order){
        order.setStatus(OrderStatusCode.STATUS_NORMAL);

        // 保存订单
        orderWholeRepository.save(order);
        // 保存明细
        orderDetailRepository.saveAll(order.getOrderDetails());

        List<OrderStrategyMapping> orderStrategyMappings = order.getOrderDetails()
                .stream()
                .map(OrderDetail::getMappings)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        // 保存策略映射
        orderStrategyMappingRepository.saveAll(orderStrategyMappings);
    }

    /**
     * 付款成功状态变更
     */
    public void paidOrderState(Long orderId) {
        Order order = orderManager.findById(orderId).orElseThrow(() -> new BizException("订单未查到"));
        if (!Objects.equals(order.getStatus(), OrderStatusCode.STATUS_NORMAL)) {
            throw new BizException("不可以操作的状态");
        }

        order.setStatus(OrderStatusCode.STATUS_NOT_YET_SHIPPED)
                .setPayTime(LocalDateTime.now());
        orderRepository.save(order);

        // 使用优惠券
        String couponIds = order.getCouponIds();
        if (StrUtil.isNotBlank(couponIds)) {
            List<Long> ids = StrUtil.split(couponIds, ",").stream()
                    .map(Long::new)
                    .collect(Collectors.toList());
//            couponClient.useBatch(ids,orderId);
        }
    }

    /**
     * 取消订单
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    public void cancelOrderState(Long orderId) {
        Order order = orderManager.findById(orderId).orElseThrow(() -> new BizException("订单未查到"));
        order.setStatus(OrderStatusCode.STATUS_CANCEL);
//        throw new BizException("回滚测试");
        orderRepository.save(order);
    }
}
