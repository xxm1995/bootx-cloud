package cn.bootx.starter.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
* 消息队列配置
* @author xxm
* @date 2021/6/25
*/
@EnableRabbit
@Configuration
public class RabbitMqConfig {

    /**
     * 添加自定义序列化
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,ObjectMapper objectMapper) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
        return rabbitTemplate;
    }

    /**
     * 添加自定义序列化
     */
    @Bean
    public DefaultMessageHandlerMethodFactory jsonHandlerMethodFactory(ObjectMapper objectMapper) {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        // 这里的转换器设置实现了 通过 @Payload 注解 自动反序列化message body
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        factory.setMessageConverter(converter);
        return factory;
    }
}
