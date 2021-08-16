package cn.bootx.ordercenter.client;

import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.ordercenter.dto.order.OrderDto;

import java.time.LocalDateTime;
import java.util.List;

/**
* 订单查询
* @author xxm
* @date 2020/11/26
*/
public interface OrderFindClient {
    /**
     * 根据用户获取订单
     */
    List<OrderDto> findByUser(Long id);

    /**
     * 订单列表 分页
     */
    PageResult<OrderDto> page(PageParam page);

    /**
     * 获取完整订单详情
     */
    OrderDto getWholeById(Long id);

    /**
     * 查询订单包含的skuIds
     */
    List<Long> findOrderSkuIds(Long orderId);

    /**
     * 获取指定类型超时订单的id集合
     */
    List<Long> findPayTimeoutOrderIdsByType(LocalDateTime date, Integer type);
}
