package cn.bootx.common.idempotency;

import cn.bootx.common.redis.RedisClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
* 幂等处理器自动配置
* @author xxm  
* @date 2021/1/2 
*/
@Configuration
@EnableConfigurationProperties({IdempotencyProperties.class, IdemRedisProperties.class})
public class IdemAutoConfiguration {

    /**
     *  自动生成的名字为 bootx.starter.dubbo.idempotency-cn.bootx.starter.idempotency.IdempotencyProperties
     *  设置一下别名, 供dubbo进行调用(dubbo通过BeanName方式查找Bean)
     */
    @Bean
    public IdempotencyProperties idempotencyProperties(IdempotencyProperties idempotencyProperties){
        return idempotencyProperties;
    }

    @Bean
    public RedisClient idemRedisClient(@Qualifier("idemRedisTemplate") StringRedisTemplate idemRedisTemplate){
        return new RedisClient(idemRedisTemplate);
    }

    /**
     * 幂等性redis配置
     */
    @Bean
    public RedisStandaloneConfiguration idemRedisConfig(IdemRedisProperties idemRedisProperties,IdempotencyProperties idempotencyProperties,
                                                        RedisStandaloneConfiguration redisStandaloneConfiguration) {
        if (idempotencyProperties.isAloneRedis()){
            RedisStandaloneConfiguration idemRedisConfig = new RedisStandaloneConfiguration();
            idemRedisConfig.setHostName(idemRedisProperties.getHost());
            idemRedisConfig.setPort(idemRedisProperties.getPort());
            idemRedisConfig.setDatabase(idemRedisProperties.getDatabase());
            idemRedisConfig.setPassword(idemRedisProperties.getPassword());
            return idemRedisConfig;
        } else {
            return redisStandaloneConfiguration;
        }
    }

    /**
     * 幂等性redis工厂
     */
    @Bean
    public LettuceConnectionFactory idemRedisFactory(GenericObjectPoolConfig<LettucePoolingClientConfiguration> config,
                                                     @Qualifier("idemRedisConfig")RedisStandaloneConfiguration idemRedisConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration
                .builder()
                .poolConfig(config)
                .build();
        return new LettuceConnectionFactory(idemRedisConfig, clientConfiguration);
    }

    /**
     * redis实例
     */
    @Bean("idemRedisTemplate")
    public StringRedisTemplate idem(@Qualifier("idemRedisFactory") RedisConnectionFactory idemRedisFactory) {
        return new StringRedisTemplate(idemRedisFactory);
    }
}
