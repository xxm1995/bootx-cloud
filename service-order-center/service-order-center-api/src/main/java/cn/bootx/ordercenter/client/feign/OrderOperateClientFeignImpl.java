package cn.bootx.ordercenter.client.feign;

import cn.bootx.ordercenter.client.OrderOperateClient;
import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.bootx.ordercenter.param.order.OrderWholeParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(OrderOperateClient.class)
@RequiredArgsConstructor
public class OrderOperateClientFeignImpl implements OrderOperateClient {
    @Autowired(required = false)
    private OrderOperateFeign orderOperateFeign;

    @Override
    public OrderDto placeOrder(OrderWholeParam orderWholeParam) {
        return orderOperateFeign.placeOrder(orderWholeParam).getData();
    }

    @Override
    public void paidOrderState(Long orderId) {
        orderOperateFeign.paidOrderState(orderId);
    }

    @Override
    public void cancelOrderState(Long orderId) {
        orderOperateFeign.cancelOrderState(orderId);
    }
}
