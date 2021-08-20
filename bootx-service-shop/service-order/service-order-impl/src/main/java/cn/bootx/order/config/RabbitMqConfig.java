package cn.bootx.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.bootx.order.event.OrderEventCode.*;

/**
* 消息队列配置
* @author xxm
* @date 2021/6/25
*/
@Configuration
public class RabbitMqConfig {

    /** 订单取消 */
    @Bean
    public Queue orderCancel() {
        return new Queue(ORDER_CANCEL);
    }
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(EXCHANGE_ORDER, true, false);
    }
    @Bean
    public Binding bindOrderCancel() {
        return BindingBuilder.bind(orderCancel())
                .to(orderExchange())
                .with(ORDER_CANCEL);
    }
}
