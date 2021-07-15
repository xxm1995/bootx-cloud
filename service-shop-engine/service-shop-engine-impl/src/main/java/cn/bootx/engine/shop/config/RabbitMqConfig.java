package cn.bootx.engine.shop.config;

import cn.bootx.paymentcenter.event.PaymentEventCode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* 消息队列配置
* @author xxm
* @date 2021/6/25
*/
@Configuration
public class RabbitMqConfig {

    public static final String PAY_COMPLETED_ENGINE = PaymentEventCode.PAY_COMPLETE+".engine";

    /** 支付完成 */
    @Bean
    public Queue payCompleted() {
        return new Queue(PAY_COMPLETED_ENGINE);
    }
    @Bean
    public DirectExchange paymentExchange() {
        return new DirectExchange(PaymentEventCode.EXCHANGE_PAYMENT);
    }

    @Bean
    public Binding bindPayCompleted() {
        return BindingBuilder.bind(payCompleted())
                .to(paymentExchange())
                .with(PaymentEventCode.PAY_COMPLETE);
    }

}
