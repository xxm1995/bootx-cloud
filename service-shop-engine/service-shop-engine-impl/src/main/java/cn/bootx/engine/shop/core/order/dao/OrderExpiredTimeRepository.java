package cn.bootx.engine.shop.core.order.dao;

import cn.bootx.starter.seata.redis.TccRedisClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * 用于订单过期时间的管理
 * 在 Redis 中每一个 token 对应的键 sku:lock:{skuId}:{token}
 * 都存储在有序集合 sku:lock:expiredtime 里，有序集合的 score 为各 token 的过期时间。
 * @author xxm
 * @date 2021/6/25
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderExpiredTimeRepository {
    private static final String KEY = "order:expiredtime";

    private final TccRedisClient redisClient;

    /**
     * 根据 orderId 存储对应的订单过期时间
     */
    public void store(String orderId, long expiredTime) {
        redisClient.zadd(KEY, orderId, expiredTime);
    }

    /**
     * 获取所有已过期的orderId
     */
    public Set<String> retrieveExpiredOrders(long deadline) {
        return redisClient.zrangeByScore(KEY, 0L, deadline);
    }

    /**
     * 删除指定的 member（即 token 对应的键）
     */
    public void removeExpiredOrder(String members) {
        redisClient.zremByMembers(KEY, members);
    }
}
