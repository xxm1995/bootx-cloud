package cn.bootx.starter.seata.redis.proxy;

import cn.bootx.starter.redis.RedisClient;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * TccRedisClient代理实现
 * @author xxm
 * @date 2021/4/28
 */
@RequiredArgsConstructor
@Component
public class TccRedisClientProxyImpl implements TccRedisClientProxy{

    private final RedisClient redisTemplate;

    private boolean notTcc(){
        return Objects.isNull(RootContext.getBranchType());
    }

    @Override
    public void deleteKey(@BusinessActionContextParameter(paramName = "key") String key) {
        if (notTcc()) {
            redisTemplate.deleteKey(key);
        }
    }

    @Override
    public void deleteKeyTcc(BusinessActionContext context) {
        String key = (String) context.getActionContext("key");
        redisTemplate.deleteKey(key);
    }

    @Override
    public void deleteKeys(@BusinessActionContextParameter(paramName = "keys") Collection<String> keys) {
        if (notTcc()) {
            redisTemplate.deleteKeys(keys);
        }
    }

    @Override
    public void deleteKeysTcc(BusinessActionContext context) {
        //noinspection unchecked
        Collection<String> keys = (Collection<String>) context.getActionContext("key");
        redisTemplate.deleteKeys(keys);
    }

    @Override
    public void set(
            @BusinessActionContextParameter(paramName = "key") String key,
            @BusinessActionContextParameter(paramName = "value") String value) {
        if (notTcc()) {
            redisTemplate.set(key, value);
        }
    }

    @Override
    public void setTcc(BusinessActionContext context) {
        String key = (String) context.getActionContext("key");
        String value = (String) context.getActionContext("value");
        redisTemplate.set(key, value);
    }

    @Override
    public void setWithTimeout(
            @BusinessActionContextParameter(paramName = "key") String key,
            @BusinessActionContextParameter(paramName = "value") String value,
            @BusinessActionContextParameter(paramName = "timeoutMs") long timeoutMs) {
        if (notTcc()) {
            redisTemplate.setWithTimeout(key, value, timeoutMs);
        }
    }

    @Override
    public void setWithTimeoutTcc(BusinessActionContext context){
        String key = (String) context.getActionContext("key");
        String value = (String) context.getActionContext("value");
        long timeoutMs = (long) context.getActionContext("timeoutMs");
        redisTemplate.setWithTimeout(key, value, timeoutMs);
    }

    @Override
    public void hset(
            @BusinessActionContextParameter(paramName = "key") String key,
            @BusinessActionContextParameter(paramName = "column") String column,
            @BusinessActionContextParameter(paramName = "value") Object value) {
        if (notTcc()) {
            redisTemplate.hset(key, column, value);
        }
    }

    @Override
    public void hsetTcc(BusinessActionContext context) {
        String key = (String) context.getActionContext("key");
        String column = (String) context.getActionContext("column");
        Object value = context.getActionContext("value");
        redisTemplate.hset(key, column, value);
    }

    @Override
    public void hmSet(@BusinessActionContextParameter(paramName = "key")String key,
                      @BusinessActionContextParameter(paramName = "map")Map<String, String> map) {
        if (notTcc()) {
            redisTemplate.hmSet(key, map);
        }
    }


    @Override
    public void hmSetTcc(BusinessActionContext context) {
        String key = (String) context.getActionContext("key");
        //noinspection unchecked
        Map<String, String> map = (Map<String, String>) context.getActionContext("map");
        redisTemplate.hmSet(key, map);
    }

    @Override
    public void expire(@BusinessActionContextParameter(paramName = "key") String key,
                       @BusinessActionContextParameter(paramName = "expire") long timeoutMs) {
        if (notTcc()) {
            redisTemplate.expire(key, timeoutMs);
        }
    }

    @Override
    public void expireTcc(BusinessActionContext context) {
        String key = (String) context.getActionContext("key");
        long timeoutMs = (long) context.getActionContext("expire");
        redisTemplate.expire(key, timeoutMs);
    }

    @Override
    public void expireUnit(@BusinessActionContextParameter(paramName = "key") String key,
                       @BusinessActionContextParameter(paramName = "expire") long expire,
                       @BusinessActionContextParameter(paramName = "timeUnit") TimeUnit timeUnit) {
        if (notTcc()) {
            redisTemplate.expireUnit(key, expire,timeUnit);
        }
    }

    @Override
    public void expireUnitTcc(BusinessActionContext context) {
        String key = (String) context.getActionContext("key");
        long expire = (long) context.getActionContext("expire");
        TimeUnit timeUnit = (TimeUnit) context.getActionContext("timeUnit");
        redisTemplate.expireUnit(key, expire,timeUnit);
    }

    @Override
    public void zadd(String key, String value, long score) {
        if (notTcc()) {
            redisTemplate.zadd(key, value,score);
        }
    }

    @Override
    public void zaddTcc(BusinessActionContext context) {
        String key = (String) context.getActionContext("key");
        String value = (String) context.getActionContext("value");
        long score = (long) context.getActionContext("score");
        redisTemplate.zadd(key, value,score);
    }

    @Override
    public void zremRangeByScore(String key, long start, long end) {
        if (notTcc()) {
            redisTemplate.zremRangeByScore(key, start,end);
        }
    }

    @Override
    public void zremRangeByScoreTcc(BusinessActionContext context) {
        String key = (String) context.getActionContext("key");
        long start = (long) context.getActionContext("start");
        long end = (long) context.getActionContext("end");
        redisTemplate.zremRangeByScore(key, start,end);
    }

    @Override
    public void zremByMembers(String key, String... members) {
        if (notTcc()) {
            redisTemplate.zremByMembers(key, members);
        }
    }

    @Override
    public void zremByMembersTcc(BusinessActionContext context) {
        String key = (String) context.getActionContext("key");
        String[] members = (String[]) context.getActionContext("members");
        redisTemplate.zremByMembers(key, members);
    }

    @Override
    public void renameTcc(
            @BusinessActionContextParameter(paramName = "oldKey") String oldKey,
            @BusinessActionContextParameter(paramName = "newKey") String newKey) {
        if (notTcc()) {
            redisTemplate.rename(oldKey,newKey);
        }
    }

    @Override
    public void renameTcc(BusinessActionContext context) {
        String oldKey = (String) context.getActionContext("oldKey");
        String newKey = (String) context.getActionContext("newKey");
        redisTemplate.rename(oldKey,newKey);
    }

    /**
     * 默认空回滚
     */
    @Override
    public void cancel(BusinessActionContext context){

    }

    @Override
    public String get(String key) {
        return redisTemplate.get(key);
    }

    @Override
    public List<String> get(Collection<String> keys) {
        return redisTemplate.get(keys);
    }

    @Override
    public Object hget(String key, String column) {
        return redisTemplate.hget(key,column);
    }

    @Override
    public Map<String, String> hmGet(String key) {
        return redisTemplate.hmGet(key);
    }

    @Override
    public Set<String> zrangeByScore(String key, long start, long end) {
        return redisTemplate.zrangeByScore(key,start,end);
    }
}
