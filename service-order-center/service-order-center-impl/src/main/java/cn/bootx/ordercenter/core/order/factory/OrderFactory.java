package cn.bootx.ordercenter.core.order.factory;

import cn.bootx.ordercenter.core.order.entity.OrderDetail;
import cn.bootx.ordercenter.core.order.entity.OrderStrategyMapping;
import cn.bootx.ordercenter.core.order.entity.OrderWhole;
import cn.bootx.ordercenter.param.order.OrderParam;
import cn.bootx.salescenter.dto.coupon.CouponDto;
import cn.bootx.salescenter.dto.order.OrderDetailPreviewResult;
import cn.bootx.salescenter.dto.order.OrderPreviewResult;
import cn.bootx.salescenter.dto.order.OrderStrategyMappingResult;
import cn.bootx.common.snowflake.SnowFlakeId;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单工厂
 * @author xxm
 * @date 2020/11/19
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFactory {
    private final SnowFlakeId snowFlakeId;


    /**
     * 转换订单为可持久化对象
     */
    public OrderWhole buildOrder(OrderPreviewResult previewResult, OrderParam orderParam){
        List<OrderDetailPreviewResult> orderDetails = previewResult.getOrderDetails();

        long orderId = snowFlakeId.nextId();

        // 明细转换为持久类
        List<OrderDetail> collect = orderDetails.stream()
                .map(o -> this.buildOrderDetail(o,orderId))
                .collect(Collectors.toList());

        String couponIds = null;
        if (CollUtil.isNotEmpty(previewResult.getCoupons())){
            couponIds = previewResult.getCoupons().stream()
                    .map(CouponDto::getId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
        }

        OrderWhole order = new OrderWhole();
        BeanUtil.copyProperties(previewResult,order);
        BeanUtil.copyProperties(orderParam,order,CopyOptions.create().ignoreNullValue());

        order.setId(orderId);
        order.setOrderDetails(collect)
                .setCouponIds(couponIds);
        return order;
    }

    /**
     * 转换订单明细
     */
    private OrderDetail buildOrderDetail(OrderDetailPreviewResult orderDetailCheckDto, long orderId){
        long orderDetailId = snowFlakeId.nextId();
        OrderDetail orderDetail = new OrderDetail();
        BeanUtil.copyProperties(orderDetailCheckDto,orderDetail, CopyOptions.create().ignoreNullValue());

        List<OrderStrategyMappingResult> mappingDtos = orderDetailCheckDto.getMappings();
        List<OrderStrategyMapping> collect = mappingDtos.stream()
                .map(o -> this.buildMapping(o,orderId,orderDetailId))
                .collect(Collectors.toList());
        orderDetail.setOrderId(orderId)
                .setGoodsTitle(orderDetailCheckDto.getGoodsTitle())
                .setMappings(collect)
                .setId(orderDetailId);
        return orderDetail;
    }



    /**
     * 转换策略映射
     */
    private OrderStrategyMapping buildMapping(OrderStrategyMappingResult strategyMappingResult, Long orderId, Long orderDetailId){
        OrderStrategyMapping orderStrategyMapping = new OrderStrategyMapping();
        BeanUtil.copyProperties(strategyMappingResult,orderStrategyMapping,CopyOptions.create().ignoreNullValue());
        orderStrategyMapping.setOrderDetailId(orderDetailId)
                .setOrderId(orderId);
        return orderStrategyMapping;
    }

}
