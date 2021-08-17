package cn.bootx.goodscenter.core.inventory.manager;

import cn.bootx.goodscenter.core.inventory.dao.InventoryRepository;
import cn.bootx.goodscenter.exception.inventory.InventoryInsufficientException;
import cn.bootx.goodscenter.exception.inventory.InventoryLockInsufficientException;
import cn.bootx.common.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
* 库存操作
* @author xxm
* @date 2020/11/22
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class InventoryManager {
    private final JPAQueryFactory jpaQueryFactory;
    private final InventoryRepository repository;

    private final HeaderHolder headerHolder;

    /**
     * 锁定库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void lockInventory(Long skuId, int count) {
        int rows = repository.lockInventory(skuId, count);
        if (rows < 1) {
            log.warn("库存操作，skuId:"+ skuId + ",库存不足，count:" + count);
            throw new InventoryInsufficientException();
        }
        log.info("库存操作，skuId:"+ skuId + ",正常锁定,count:" + count);
    }

    /**
     * 解锁库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void unlockInventory(Long skuId, int count) {
        int rows = repository.unlockInventory(skuId, count);
        if (rows < 1) {
            log.warn("库存操作，skuId:"+ skuId + ",解锁库存不足，count:" + count);
            throw new InventoryLockInsufficientException();
        }
        log.info("库存操作，skuId:"+ skuId + ",库存正常解锁，count:" + count);
    }

    /**
     * 减少库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void reduceInventory(Long skuId, int count) {
        int rows = repository.reduceInventory(skuId, count);
        if (rows < 1) {
            log.warn("库存操作，skuId:"+ skuId + ",库存不足，count:" + count);
            throw new InventoryLockInsufficientException();
        }
        log.info("库存操作，skuId:"+ skuId + ",库存正常扣除，count:" + count);
    }

    /**
     * 增补指定 SKU 的可售库存， 扣减对应售出
     */
    @Transactional(rollbackFor = Exception.class)
    public void increaseInventory(Long skuId, int amount) {
        repository.increaseInventory(skuId, amount);
        log.info("库存操作，skuId:"+ skuId + ",increaseInventory，count:" + amount);
    }

    /**
     * 增补指定 SKU 的可售库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void increaseAvailable(Long skuId, int amount) {
        repository.increaseAvailable(skuId, amount);
        log.info("库存操作，skuId:"+ skuId + ",increaseAvailable，count:" + amount);
    }

    /**
     * 扣减指定 SKU 的预占库存并减少总库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void reduceLockedAndCapacity(Long skuId, int amount) {
        int rows = repository.reduceLockedAndCapacity(skuId, amount);
        if (rows < 1) {
            log.warn("库存操作，skuId:"+ skuId + ",扣减库存总数库存不足，count:" + amount);
            throw new InventoryLockInsufficientException();
        }
        log.info("库存操作，skuId:"+ skuId + ",扣减库存总数正常扣除，count:" + amount);
    }

    /**
     * 扣减指定 SKU 的售出库存并减少总库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void reduceSoldAndCapacity(Long skuId, int amount) {
        int rows = repository.reduceSoldAndCapacity(skuId, amount);
        if (rows < 1) {
            log.warn("库存操作，skuId:"+ skuId + ",扣减库存总数和sold库存不足，count:" + amount);
            throw new InventoryInsufficientException();
        }
        log.info("库存操作，skuId:"+ skuId + ",扣减库存总数和sold正常扣除，count:" + amount);
    }
}
