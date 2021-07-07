package cn.bootx.goodscenter.client;

import cn.bootx.goodscenter.dto.inventory.LockInventoryDto;

/**   
* 库存管理
* @author xxm  
* @date 2020/11/26 
*/
public interface InventoryClient {
    /**
     * 获取指定 SKU 的可用库存
     */
    Integer getAvailable(Long skuId);

    /**
     * 预占指定 SKU 的库存，返回授权码，需要在调用释放或扣减库存API时使用
     */
    LockInventoryDto lockInventory(Long skuId, int count);

    /**
     * 释放指定的 SKU 的库存，需要使用预占库存时分配的 token
     */
    void unlockInventory(Long skuId, int count, String token);

    /**
     * 解锁没有令牌的库存
     */
    void unlockInventoryWithoutToken(Long skuId, int count);

    /**
     * 扣减指定 SKU 的预占库存，增加对应售出，需要使用预占库存时分配的 token
     */
    void reduceInventory(Long skuId, int count, String token);

    /**
     * 增补指定 SKU 的可售库存， 扣减对应售出
     */
    void increaseInventory(Long skuId, int count);

    /**
     * 增补指定 SKU 的可售库存
     */
    void increaseAvailable(Long skuId, int count);

    /**
     * 扣减指定的 SKU 的库存
     */
    void reduceLockedAndCapacity(Long skuId, int count, String token);

    /**
     * 扣减指定 SKU 的售出库存并减少总库存
     */
    void reduceSoldAndCapacity(Long skuId, int count);
}
