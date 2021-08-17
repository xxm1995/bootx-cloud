package cn.bootx.common.seata.redis;

import cn.bootx.common.seata.redis.proxy.TccRedisClientProxy;
import io.seata.rm.tcc.api.LocalTCC;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 带有tcc事务的redis客户端
 * @author xxm
 * @date 2021/4/28
 */
@LocalTCC
@RequiredArgsConstructor
public class TccRedisClient {
    private final TccRedisClientProxy tccRedisClientProxy;

    public void deleteKey(String key) {
        tccRedisClientProxy.deleteKey(key);
    }

    public void deleteKeys(Collection<String> keys) {
        tccRedisClientProxy.deleteKeys(keys);
    }

    public void set(String key, String value) {
        tccRedisClientProxy.set(key, value);
    }

    public void setWithTimeout(String key, String value, long timeoutMs) {
        tccRedisClientProxy.setWithTimeout(key, value, timeoutMs);
    }


    public void hset(String key, String column, Object value) {
        tccRedisClientProxy.hset(key, column, value);
    }


    public void hmset(String key, Map<String, String> map) {
        tccRedisClientProxy.hmSet(key, map);
    }

    public void expire(String key, long timeoutMs) {
        tccRedisClientProxy.expire(key, timeoutMs);
    }

    public void zadd(String key, String value, long score) {
        tccRedisClientProxy.zadd(key, value,score);
    }

    public void zremRangeByScore(String key, long start, long end) {
        tccRedisClientProxy.zremRangeByScore(key,start,end);
    }

    public Set<String> zrangeByScore(String key, long start, long end) {
        return tccRedisClientProxy.zrangeByScore(key,start,end);
    }

    public void zremByMembers(String key, String... members) {
        tccRedisClientProxy.zremByMembers(key,members);
    }
    public void rename(String oldKey, String newKey) {
        tccRedisClientProxy.renameTcc(oldKey, newKey);
    }

    public String get(String key) {
        return tccRedisClientProxy.get(key);
    }

    public List<String> get(Collection<String> keys) {
        return tccRedisClientProxy.get(keys);
    }

    public Object hget(String key, String column) {
        return tccRedisClientProxy.hget(key,column);
    }

    public Map<String, String> hmGet(String key) {
        return tccRedisClientProxy.hmGet(key);
    }
}
