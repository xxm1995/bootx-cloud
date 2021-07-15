package cn.bootx.salescenter.core.calculate.service;

import cn.bootx.salescenter.core.calculate.cache.CalculateCache;
import cn.bootx.salescenter.core.calculate.cache.OrderCache;
import cn.bootx.salescenter.core.calculate.factory.CalculateFactory;
import cn.bootx.salescenter.dto.order.OrderPreviewResult;
import cn.bootx.salescenter.param.order.OrderCheckParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 订单计算预览, 使用平行优惠
 * @author xxm
 * @date 2020/10/16
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderPreviewService {
    private final CalculateFactory calculateFactory;
    private final OrderCalculateService orderCalculateService;

    /**
     * 费用计算预览
     */
    public OrderPreviewResult previewOrderPrice(OrderCheckParam orderParam) {
        // 无缓存构建订单
        CalculateFactory.OrderAndBuffer orderDtoAndBuffer = calculateFactory.buildOrderDto(orderParam);
        OrderCache orderCache = orderDtoAndBuffer.getOrderCache();
        CalculateCache calculateCache = orderDtoAndBuffer.getCalculateCache();
        // 计算价格
        return orderCalculateService.calculateOrderPrice(orderCache, calculateCache);
    }

    /**
     * 费用计算预览(不检查合法性)
     */
    public OrderPreviewResult previewOrderPriceNoCheck(OrderCheckParam orderParam){
        // 无缓存构建订单
        CalculateFactory.OrderAndBuffer orderDtoAndBuffer = calculateFactory.buildOrderDto(orderParam);
        OrderCache orderCache = orderDtoAndBuffer.getOrderCache();
        CalculateCache calculateCache = orderDtoAndBuffer.getCalculateCache();

        return orderCalculateService.calculateOrderPriceNoCheck(orderCache,calculateCache);
    }
}
