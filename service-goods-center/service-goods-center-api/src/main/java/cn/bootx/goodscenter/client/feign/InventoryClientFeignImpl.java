package cn.bootx.goodscenter.client.feign;

import cn.bootx.goodscenter.client.InventoryClient;
import cn.bootx.goodscenter.dto.inventory.LockInventoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(InventoryClient.class)
public class InventoryClientFeignImpl implements InventoryClient {
    @Autowired(required = false)
    private InventoryFeign inventoryFeign;

    @Override
    public Integer getAvailable(Long skuId) {
        return inventoryFeign.getAvailable(skuId).getData();
    }

    @Override
    public LockInventoryDto lockInventory(Long skuId, int count) {
        return inventoryFeign.lockInventory(skuId,count).getData();
    }

    @Override
    public void unlockInventory(Long skuId, int count, String token) {
        inventoryFeign.unlockInventory(skuId,count,token);
    }

    @Override
    public void unlockInventoryWithoutToken(Long skuId, int count) {
        inventoryFeign.unlockInventoryWithoutToken(skuId,count);
    }

    @Override
    public void reduceInventory(Long skuId, int count, String token) {
        inventoryFeign.reduceInventory(skuId,count,token);
    }

    @Override
    public void increaseInventory(Long skuId, int count) {
        inventoryFeign.increaseInventory(skuId,count);
    }

    @Override
    public void increaseAvailable(Long skuId, int count) {
        inventoryFeign.increaseAvailable(skuId,count);
    }

    @Override
    public void reduceLockedAndCapacity(Long skuId, int count, String token) {
        inventoryFeign.reduceLockedAndCapacity(skuId,count,token);
    }

    @Override
    public void reduceSoldAndCapacity(Long skuId, int count) {
        inventoryFeign.reduceSoldAndCapacity(skuId,count);
    }
}
