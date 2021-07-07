package cn.bootx.goodscenter.core.inventory.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.goodscenter.core.inventory.handler.PackedInventoryHandler;
import cn.bootx.goodscenter.core.inventory.manager.InventoryManager;
import cn.bootx.goodscenter.core.inventory.manager.InventoryTokenContainer;
import cn.bootx.goodscenter.core.packing.dao.GoodsSkuPackManager;
import cn.bootx.goodscenter.core.packing.entity.GoodsSkuPack;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuManager;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.dto.inventory.LockInventoryDto;
import cn.bootx.goodscenter.exception.inventory.InventoryNotFoundException;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 库存操作
 * @author xxm
 * @date 2021/2/7
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OperateInventoryService {
    private final InventoryTokenContainer tokenContainer;

    private final GoodsSkuManager skuManager;
    private final GoodsSkuPackManager skuPackingManager;
    private final InventoryManager inventoryManager;

    /**
     * 预占指定 SKU 的库存，返回授权码，需要在调用释放或扣减库存API时使用
     * 使用分布式事务
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    public LockInventoryDto lockInventory(Long skuId, int count) {
        if (count < 1){
            throw new BizException("要锁定的库存不得小于1");
        }

        LockInventoryDto result = new LockInventoryDto();
        result.setCount(count);
        GoodsSku sku = skuManager.findById(skuId).orElseThrow(InventoryNotFoundException::new);

        // 处理打包品的预占库存
        if (sku.isPacking()){
            this.inventory4Packing(skuId, count, inventoryManager::lockInventory);
        }
        inventoryManager.lockInventory(skuId, count);

        result.setStartIndex(sku.getLocked() + sku.getSold() + 1);
        // 记录锁定数和过期时间
        result.setToken(tokenContainer.saveAndGetToken(skuId, count));
        return result;
    }

    /**
     * 释放指定的 SKU 的库存，需要使用预占库存时分配的 token
     */
    @Transactional(rollbackFor = Exception.class)
    public void unlockInventory(Long skuId, int count, String token) {
        // 解锁库存
        this.unlockInventoryWithoutToken(skuId, count);
        tokenContainer.consumeInventory(skuId, count, token);
    }

    /**
     * 解锁没有令牌的库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void unlockInventoryWithoutToken(Long skuId, int count) {
        GoodsSku goodsSku = skuManager.findById(skuId).orElseThrow(InventoryNotFoundException::new);
        // 打包品处理
        if (goodsSku.isPacking()) {
            this.inventory4Packing(skuId, count, inventoryManager::unlockInventory);
        }
        // 自身处理
        inventoryManager.unlockInventory(skuId, count);
    }

    /**
     * 扣减指定的 SKU 的库存，需要使用预占库存时分配的 token
     */
    @Transactional(rollbackFor = Exception.class)
    public void reduceInventory(Long skuId, int count, String token) {
        GoodsSku goodsSku = skuManager.findById(skuId).orElseThrow(InventoryNotFoundException::new);
        // 打包品处理
        if (goodsSku.isPacking()) {
            this.inventory4Packing(skuId, count, inventoryManager::reduceInventory);
        }
        inventoryManager.reduceInventory(skuId, count);
        tokenContainer.consumeInventory(skuId, count, token);
    }

    /**
     * 增补指定 SKU 的可售库存， 扣减对应售出
     */
    @Transactional(rollbackFor = Exception.class)
    public void increaseInventory(Long skuId, int count) {
        GoodsSku goodsSku = skuManager.findById(skuId).orElseThrow(InventoryNotFoundException::new);
        // 打包品处理
        if (goodsSku.isPacking()) {
            this.inventory4Packing(skuId, count, inventoryManager::increaseInventory);
        }
        inventoryManager.increaseInventory(skuId, count);
    }

    /**
     * 增补指定 SKU 的可售库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void increaseAvailable(Long skuId, int count) {
        GoodsSku goodsSku = skuManager.findById(skuId).orElseThrow(InventoryNotFoundException::new);
        // 打包品处理
        if (goodsSku.isPacking()) {
            this.inventory4Packing(skuId, count, inventoryManager::increaseAvailable);
        }
        inventoryManager.increaseAvailable(skuId, count);
    }

    /**
     * 扣减指定 SKU 的预占库存并减少总库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void reduceLockedAndCapacity(Long skuId, int count, String token) {
        GoodsSku goodsSku = skuManager.findById(skuId).orElseThrow(InventoryNotFoundException::new);
        // 打包品处理
        if (goodsSku.isPacking()) {
            this.inventory4Packing(skuId, count, inventoryManager::reduceLockedAndCapacity);
        }
        tokenContainer.consumeInventory(skuId, count, token);
        inventoryManager.reduceLockedAndCapacity(skuId, count);
    }

    /**
     * 扣减指定 SKU 的售出库存并减少总库存
     */
    @Transactional(rollbackFor = Exception.class)
    public void reduceSoldAndCapacity(Long skuId, int count) {
        GoodsSku goodsSku = skuManager.findById(skuId).orElseThrow(InventoryNotFoundException::new);

        // 打包品处理
        if (goodsSku.isPacking()) {
            this.inventory4Packing(skuId, count, inventoryManager::reduceSoldAndCapacity);
        }
        inventoryManager.reduceSoldAndCapacity(skuId, count);
    }

    /**
     * 打包品处理
     * @param skuId skuId
     * @param count 数量
     * @param handler 被打包商品库存处理器
     */
    private void inventory4Packing(Long skuId, int count, PackedInventoryHandler handler) {

        // 自身是打包品，则处理被打包品的库存
        List<GoodsSkuPack> goodsSkuPacks = skuPackingManager.findBySkuId(skuId);

        // 处理关联的被打包品
        goodsSkuPacks.forEach(packedSku -> handler.handle(packedSku.getPackedSkuId(), count));
    }
}