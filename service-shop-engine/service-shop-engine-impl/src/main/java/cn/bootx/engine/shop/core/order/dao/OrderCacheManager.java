package cn.bootx.engine.shop.core.order.dao;

import cn.bootx.goodscenter.code.GoodsCenterCode;
import cn.bootx.starter.seata.redis.TccRedisClient;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**   
* 订单缓存管理器
* @author xxm  
* @date 2021/4/12
*/
@Repository
@RequiredArgsConstructor
public class OrderCacheManager {
    private static final String SPLITTER = ":";

    private static final String INVENTORY_LOCK_TOKEN_PREFIX = "lock:inventory:order_";
    private static final long TIMEOUT_30_M = 30 * 60 * 1000L;

    private final OrderExpiredTimeRepository expiredTimeRepository;
    private final TccRedisClient redisClient;

    /**
     * 保存库存锁定令牌
     */
    public void saveInventoryLockToken(Long orderId, Long skuId, String token) {
        String key = buildCardLockTokenKey(orderId, skuId);
        redisClient.setWithTimeout(key, token, TIMEOUT_30_M);
    }

    /**
     * 获取Sku Lock令牌
     */
    public String getSkuLockToken(Long orderId, Long skuId) {
        String key = buildCardLockTokenKey(orderId, skuId);
        return redisClient.get(key);
    }

    /**
     * 删除redis 订单库存Token 缓存
     */
    public void deleteSkuLockToken(Long orderId, Long skuId) {
        String key = buildCardLockTokenKey(orderId, skuId);
        redisClient.deleteKey(key);
    }

    private String buildCardLockTokenKey(Long orderId, Long skuId) {
        return INVENTORY_LOCK_TOKEN_PREFIX + orderId + SPLITTER + skuId;
    }

    /**
     * 通过token存储 订单过期时间
     */
    public void storeByOrderId(Long orderId) {
        // 过期时间
        long expiredTime = this.calculateExpiredTime();
        expiredTimeRepository.store(String.valueOf(orderId), expiredTime);
    }

    /**
     * 返回所有已过期的 订单id
     */
    public List<Long> retrieveExpiredOrders() {
        long deadline = System.currentTimeMillis();
        Set<String> orders = expiredTimeRepository.retrieveExpiredOrders(deadline);
        if (CollUtil.isNotEmpty(orders)){
            return orders.stream()
                    .map(Long::valueOf)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>(0);
    }

    /**
     * 删除订单过期时间信息
     */
    public void removeExpiredOrder(Long orderId) {
        expiredTimeRepository.removeExpiredOrder(String.valueOf(orderId));
    }


    /**
     * 计算过期时间
     */
    private long calculateExpiredTime() {
        return System.currentTimeMillis() + GoodsCenterCode.INVENTORY_TOKEN_EXPIRED_TIME;
    }
}
