package cn.bootx.common.seata;

import cn.bootx.common.redis.RedisClient;
import cn.bootx.common.seata.redis.TccRedisClient;
import cn.bootx.common.seata.redis.proxy.TccRedisClientProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* seata自动配置
* @author xxm
* @date 2021/4/28
*/
@Configuration
@ConditionalOnClass(RedisClient.class)
@RequiredArgsConstructor
public class SeataAutoConfiguration {

    /**
     * 默认 RedisClient
     */
    @Bean
    public TccRedisClient tccRedisClient(TccRedisClientProxy tccRedisClientProxy) {
        return new TccRedisClient(tccRedisClientProxy);
    }

}
