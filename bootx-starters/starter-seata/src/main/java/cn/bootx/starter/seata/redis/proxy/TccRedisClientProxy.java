package cn.bootx.starter.seata.redis.proxy;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * TccRedisClient代理
 * @author xxm
 * @date 2021/4/28
 */
@LocalTCC
public interface TccRedisClientProxy {

    @TwoPhaseBusinessAction(name = "deleteKey", commitMethod = "deleteKeyTcc", rollbackMethod = "cancel")
    void deleteKey(@BusinessActionContextParameter(paramName = "key") String key);

    void deleteKeyTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "deleteKeys", commitMethod = "deleteKeysTcc", rollbackMethod = "cancel")
    void deleteKeys(@BusinessActionContextParameter(paramName = "keys") Collection<String> keys);

    void deleteKeysTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "set", commitMethod = "setTcc", rollbackMethod = "cancel")
    void set(
            @BusinessActionContextParameter(paramName = "key") String key,
            @BusinessActionContextParameter(paramName = "value") String value);

    void setTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "setWithTimeout", commitMethod = "setWithTimeoutTcc", rollbackMethod = "cancel")
    void setWithTimeout(
            @BusinessActionContextParameter(paramName = "key") String key,
            @BusinessActionContextParameter(paramName = "value") String value,
            @BusinessActionContextParameter(paramName = "timeoutMs") long timeoutMs
    );

    void setWithTimeoutTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "hset", commitMethod = "hsetTcc", rollbackMethod = "cancel")
    void hset(
            @BusinessActionContextParameter(paramName = "key") String key,
            @BusinessActionContextParameter(paramName = "column") String column,
            @BusinessActionContextParameter(paramName = "value") Object value);

    void hsetTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "hmset", commitMethod = "hmSetTcc", rollbackMethod = "cancel")
    void hmSet(@BusinessActionContextParameter(paramName = "key") String key,
               @BusinessActionContextParameter(paramName = "map") Map<String, String> map);

    void hmSetTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "expire", commitMethod = "expireTcc", rollbackMethod = "cancel")
    void expire(@BusinessActionContextParameter(paramName = "key") String key,
                @BusinessActionContextParameter(paramName = "timeoutMs") long timeoutMs);
    void expireTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "expireUnit", commitMethod = "expireUnitTcc", rollbackMethod = "cancel")
    void expireUnit(@BusinessActionContextParameter(paramName = "key") String key,
                @BusinessActionContextParameter(paramName = "timeoutMs") long timeoutMs,
                @BusinessActionContextParameter(paramName = "timeUnit") TimeUnit timeUnit);

    void expireUnitTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "zadd", commitMethod = "zaddTcc", rollbackMethod = "cancel")
    void zadd(@BusinessActionContextParameter(paramName = "key") String key,
              @BusinessActionContextParameter(paramName = "value") String value,
              @BusinessActionContextParameter(paramName = "score") long score);

    void zaddTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "zremRangeByScore", commitMethod = "zremRangeByScoreTcc", rollbackMethod = "cancel")
    void zremRangeByScore(@BusinessActionContextParameter(paramName = "key")String key,
                          @BusinessActionContextParameter(paramName = "start")long start,
                          @BusinessActionContextParameter(paramName = "end")long end);

    void zremRangeByScoreTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "zremByMembers", commitMethod = "zremByMembersTcc", rollbackMethod = "cancel")
    void zremByMembers(@BusinessActionContextParameter(paramName = "key") String key,
                       @BusinessActionContextParameter(paramName = "members") String... members);

    void zremByMembersTcc(BusinessActionContext context);

    @TwoPhaseBusinessAction(name = "rename", commitMethod = "renameTcc", rollbackMethod = "cancel")
    void renameTcc(
            @BusinessActionContextParameter(paramName = "oldKey") String oldKey,
            @BusinessActionContextParameter(paramName = "newKey") String newKey);

    void renameTcc(BusinessActionContext context);

    void cancel(BusinessActionContext context);

    String get(String key);

    List<String> get(Collection<String> keys);

    Object hget(String key, String column);

    Map<String, String> hmGet(String key);

    Set<String> zrangeByScore(String key, long start, long end);
}
