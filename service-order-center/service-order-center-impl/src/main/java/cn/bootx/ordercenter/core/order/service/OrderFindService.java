package cn.bootx.ordercenter.core.order.service;

import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.core.util.ResultConvertUtils;
import cn.bootx.ordercenter.core.order.dao.OrderDetailManager;
import cn.bootx.ordercenter.core.order.dao.OrderManager;
import cn.bootx.ordercenter.core.order.dao.OrderStrategyMappingManager;
import cn.bootx.ordercenter.core.order.dao.OrderWholeManager;
import cn.bootx.ordercenter.core.order.entity.OrderDetail;
import cn.bootx.ordercenter.core.order.entity.OrderStrategyMapping;
import cn.bootx.ordercenter.core.order.entity.OrderWhole;
import cn.bootx.ordercenter.dto.order.OrderDetailDto;
import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.bootx.ordercenter.dto.order.OrderStrategyMappingDto;
import cn.bootx.ordercenter.exception.order.OrderNotExistException;
import cn.bootx.starter.jpa.utils.JpaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单查询
 * @author xxm
 * @date 2020/11/26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFindService {
    private final OrderManager orderManager;
    private final OrderWholeManager orderWholeManager;
    private final OrderDetailManager orderDetailManager;
    private final OrderStrategyMappingManager mappingManager;

    /**
     * 根据用户获取订单
     */
    public List<OrderDto> findByUser(Long id){
        return ResultConvertUtils.dtoListConvert(orderManager.findByUser(id));
    }

    /**
     * 订单列表 分页
     */
    public PageResult<OrderDto> page(PageParam page){
        return JpaUtils.convert2PageResult(orderManager.page(page));
    }

    /**
     * 订单详情
     */
    public OrderDto getWholeById(Long id){

        // 订单
        OrderWhole order = orderWholeManager.findById(id).orElseThrow(OrderNotExistException::new);
        OrderDto orderDto = order.toDto();

        // 获取生效的优惠
        Map<Long, List<OrderStrategyMappingDto>> mappingMap = mappingManager.findByOrder(id).stream()
                .map(OrderStrategyMapping::toDto)
                .collect(Collectors.groupingBy(OrderStrategyMappingDto::getOrderDetailId));

        // 获取明细
        List<OrderDetailDto> details = orderDetailManager.findByOrder(id)
                .stream()
                .map(OrderDetail::toDto)
                .map(orderDetailDto -> orderDetailDto.setMappings(mappingMap.get(orderDetailDto.getId())))
                .collect(Collectors.toList());
        orderDto.setDetails(details);

        return orderDto;
    }

    /**
     * 获取订单中的sku列表
     */
    public List<Long> findOrderSkuIds(Long orderId) {
        return orderManager.findOrderSkuIds(orderId);
    }

    /**
     * 获取指定类型超时订单的id集合
     */
    public List<Long> findPayTimeoutOrderIdsByType(LocalDateTime date,Integer type){
        return orderManager.findPayTimeoutOrderIdsByType(date,type);
    }

}
