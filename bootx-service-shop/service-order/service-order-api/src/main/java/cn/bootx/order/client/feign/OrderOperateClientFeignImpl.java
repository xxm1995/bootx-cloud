package cn.bootx.order.client.feign;

import cn.bootx.order.client.OrderOperateClient;
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
    public void paidOrderState(Long orderId) {
        orderOperateFeign.paidOrderState(orderId);
    }

    @Override
    public void cancelOrderState(Long orderId) {
        orderOperateFeign.cancelOrderState(orderId);
    }
}
