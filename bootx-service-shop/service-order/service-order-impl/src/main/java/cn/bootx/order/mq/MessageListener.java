package cn.bootx.order.mq;

import cn.bootx.order.core.order.service.OrderOperateService;
import cn.bootx.order.event.OrderEventCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 消息接收
 * @author xxm
 * @date 2021/4/22
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageListener {
    private final OrderOperateService orderOperateService;

    /**
     * 订单取消事件处理
     */
    @RabbitListener(queues = OrderEventCode.ORDER_CANCEL)
    public void cancelOrderState(@Payload Long orderId) {
        log.info("订单撤销事件:{}",orderId);
        orderOperateService.cancelOrderState(orderId);
    }
}
