package cn.bootx.starter.cache.tenant;

import cn.bootx.starter.headerholder.HeaderHolder;
import cn.hutool.core.util.StrUtil;
import lombok.Setter;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

/**
* 自定义Redis缓存管理
* @author xxm
* @date 2021/6/11
*/
public class TenantRedisCacheManager extends RedisCacheManager {
    @Setter
    private HeaderHolder headerHolder;
    @Setter
    private Map<String,Integer> keysTtl;
    private final RedisCacheWriter cacheWriter;

    public TenantRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
        this.cacheWriter = cacheWriter;
    }

    public TenantRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
        this.cacheWriter = cacheWriter;
    }

    public TenantRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, boolean allowInFlightCacheCreation, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
        this.cacheWriter = cacheWriter;
    }

    public TenantRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
        this.cacheWriter = cacheWriter;
    }

    public TenantRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
        this.cacheWriter = cacheWriter;
    }

    /**
     * redis记录Key拼入
     */
    @Override
    @Nullable
    @SuppressWarnings("ConstantConditions")
    public Cache getCache(@Nullable String name) {
        Long tid = headerHolder.getTid();
        String cacheName = tid == null ? name : name+":"+tid;
        return super.getCache(cacheName);
    }

    @Override
    @SuppressWarnings({"ConstantConditions", "NullableProblems"})
    protected RedisCache createRedisCache(@Nullable String name, @Nullable RedisCacheConfiguration cacheConfig) {
        Optional<String> keyOptional = keysTtl.keySet().stream()
                .sorted((o1, o2) -> StrUtil.compare(o2,o1,false))
                .filter(name::startsWith)
                .findFirst();
        // 是自定义的key
        if (keyOptional.isPresent()){
            String key = keyOptional.get();
            return this.createBootxRedisCache(name, cacheConfig.entryTtl(Duration.ofSeconds(keysTtl.get(key))));
        }
        return this.createBootxRedisCache(name, cacheConfig);
    }

    /**
     * 替换为自定义的RedisCache
     */
    public BootxRedisCache createBootxRedisCache(String name, RedisCacheConfiguration cacheConfig){
        return new BootxRedisCache(name, this.cacheWriter, cacheConfig);
    }
}

