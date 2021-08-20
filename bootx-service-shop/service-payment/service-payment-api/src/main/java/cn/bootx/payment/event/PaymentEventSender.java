package cn.bootx.payment.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 支付中心发送
 * @author xxm
 * @date 2021/4/22
 */
@Slf4j
@RequiredArgsConstructor
public class PaymentEventSender {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 发送撤销支付事件
     */
    public void sendCancelPay(Long paymentId){
        rabbitTemplate.convertAndSend(
                PaymentEventCode.EXCHANGE_PAYMENT,
                PaymentEventCode.PAY_COMPLETE,
                paymentId
        );
    }
}
