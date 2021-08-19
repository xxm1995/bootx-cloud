package cn.bootx.paymentcenter.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.bootx.paymentcenter.event.PaymentEventCode.*;

/**
* 消息队列配置
* @author xxm
* @date 2021/6/25
*/
@Configuration
public class RabbitMqConfig {

    /** 支付撤销 */
    @Bean
    public Queue payCancel() {
        return new Queue(PAY_CANCEL);
    }
    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange(EXCHANGE_PAYMENT);
    }
    @Bean
    public Binding bindPayCancel() {
        return BindingBuilder.bind(payCancel())
                .to(paymentExchange())
                .with(PAY_CANCEL);
    }

}
