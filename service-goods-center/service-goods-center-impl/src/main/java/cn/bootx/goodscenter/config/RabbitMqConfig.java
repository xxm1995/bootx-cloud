package cn.bootx.goodscenter.config;

import cn.bootx.goodscenter.event.GoodsEventCode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.bootx.goodscenter.event.GoodsEventCode.*;

/**
* 消息队列配置
* @author xxm
* @date 2021/6/25
*/
@Configuration
public class RabbitMqConfig {

    /** 库存操作路由器 */
    @Bean
    public DirectExchange goodsExchange() {
        return new DirectExchange(EXCHANGE_GOODS);
    }

    /** 解锁库存 */
    @Bean
    public Queue unlockInventory() {
        return new Queue(UNLOCK_INVENTORY);
    }
    @Bean
    public Binding bindUnlockInventory() {
        return BindingBuilder.bind(unlockInventory()).to(goodsExchange()).with(UNLOCK_INVENTORY);
    }

    /** 扣库存 */
    @Bean
    public Queue reduceInventory() {
        return new Queue(GoodsEventCode.REDUCE_INVENTORY);
    }
    @Bean
    public Binding bindReduceInventory() {
        return BindingBuilder.bind(reduceInventory()).to(goodsExchange()).with(REDUCE_INVENTORY);
    }
}
