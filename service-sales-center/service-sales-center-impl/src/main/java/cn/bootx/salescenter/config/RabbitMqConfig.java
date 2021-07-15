package cn.bootx.salescenter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.bootx.salescenter.event.SalesEventCode.*;

/**
* 消息队列配置
* @author xxm
* @date 2021/6/25
*/
@Configuration
public class RabbitMqConfig {

    /** 库存操作路由器 */
    @Bean
    public DirectExchange salesExchange() {
        return new DirectExchange(EXCHANGE_SALES);
    }

    /** 使用优惠券事件 */
    @Bean
    public Queue useCoupon() {
        return new Queue(USE_COUPON);
    }
    @Bean
    public Binding bindUseCoupon() {
        return BindingBuilder.bind(useCoupon())
                .to(salesExchange())
                .with(USE_COUPON);
    }

    /** 教唆优惠券 */
    @Bean
    public Queue unlockCoupon() {
        return new Queue(UNLOCK_COUPON);
    }
    @Bean
    public Binding bindUnlockCoupon() {
        return BindingBuilder.bind(unlockCoupon()).to(salesExchange()).with(UNLOCK_COUPON);
    }
}
