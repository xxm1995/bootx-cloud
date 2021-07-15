package cn.bootx.ordercenter.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 订单中心发送
 * @author xxm
 * @date 2021/4/22
 */
@Slf4j
@RequiredArgsConstructor
public class OrderEventSender {
    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送取消订单信息
     */
    public void sendCancelOrder(Long orderId){
        rabbitTemplate.convertAndSend(
                OrderEventCode.EXCHANGE_ORDER,
                OrderEventCode.ORDER_CANCEL,
                orderId
        );
    }

}
