package cn.bootx.order.client.feign;

import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.order.client.OrderFindClient;
import cn.bootx.order.dto.order.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@ConditionalOnMissingBean(OrderFindClient.class)
@RequiredArgsConstructor
public class OrderFindClientFeignImpl implements OrderFindClient {
    @Autowired(required = false)
    private OrderFindFeign orderFindFeign;
    @Override
    public List<OrderDto> findByUser(Long id) {
        return orderFindFeign.findByUser(id).getData();
    }

    @Override
    public PageResult<OrderDto> page(PageParam page) {
        return orderFindFeign.page(page).getData();
    }

    @Override
    public OrderDto getWholeById(Long id) {
        return orderFindFeign.getWholeById(id).getData();
    }

    @Override
    public List<Long> findOrderSkuIds(Long orderId) {
        return orderFindFeign.findOrderSkuIds(orderId).getData();
    }

    @Override
    public List<Long> findPayTimeoutOrderIdsByType(LocalDateTime date, Integer type) {
        return orderFindFeign.findPayTimeoutOrderIdsByType(date,type).getData();
    }
}
