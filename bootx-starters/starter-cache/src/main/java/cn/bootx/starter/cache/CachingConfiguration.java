package cn.bootx.starter.cache;

import cn.bootx.starter.cache.tenant.TenantRedisCacheManager;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * 缓存自动配置
 * @author xxm
 * @date 2021/6/11
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties(CachingProperties.class)
@ConditionalOnClass(CacheManager.class)
@ConditionalOnProperty(prefix = "bootx.starter.cache", value = "enabled", havingValue = "true",matchIfMissing = true)
@RequiredArgsConstructor
public class CachingConfiguration extends CachingConfigurerSupport {

    private final CachingProperties cachingProperties;
    private final HeaderHolder headerHolder;
    private final ObjectMapper objectMapper;

    /**
     * 不配置key的情况,将方法名作为缓存key名称
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> method.getName();
    }

    /**
     * 缓存管理器
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        TenantRedisCacheManager tenantRedisCacheManager = new TenantRedisCacheManager(
                //Redis 缓存写入器
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                // 默认配置
                this.getRedisCacheConfigurationWithTtl(Duration.ofSeconds(cachingProperties.getDefaultTtl()))
        );
        tenantRedisCacheManager.setHeaderHolder(headerHolder);
        tenantRedisCacheManager.setKeysTtl(cachingProperties.getKeysTtl());
        return tenantRedisCacheManager;

    }

    /**
     * 缓存管理器策略过期时间配置 `
     */
    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Duration duration) {
        // 序列化方式
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapperCopy = objectMapper.copy();
        //指定序列化输入的类型为非最终类型，除了少数“自然”类型（字符串、布尔值、整数、双精度），它们可以从 JSON 正确推断； 以及所有非最终类型的数组
        objectMapperCopy.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY)
                // null不序列化
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapperCopy);

        // redis缓存配置
        return RedisCacheConfiguration
                .defaultCacheConfig()
                // 设置key为String
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置value 为自动转Json的Object
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                // 不缓存null
                .disableCachingNullValues()
                // 覆盖默认的构造key，否则会多出一个冒号
                .computePrefixWith(name -> name + ":")
                // 过期时间
                .entryTtl(duration);
    }
}
