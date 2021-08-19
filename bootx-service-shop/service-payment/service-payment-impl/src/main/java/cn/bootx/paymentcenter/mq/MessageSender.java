package cn.bootx.paymentcenter.mq;

import cn.bootx.paymentcenter.dto.pay.PayResult;
import cn.bootx.paymentcenter.event.PaymentEventCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息发送器
 * @author xxm
 * @date 2021/4/22
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;

    /**
     * 支付完成 事件发布
     */
    public void sendPaymentCompleted(PayResult event){
        rabbitTemplate.convertAndSend(
                PaymentEventCode.EXCHANGE_PAYMENT,
                PaymentEventCode.PAY_COMPLETE,
                event
        );
    }
}
