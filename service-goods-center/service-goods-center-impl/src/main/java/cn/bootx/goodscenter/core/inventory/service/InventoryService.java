package cn.bootx.goodscenter.core.inventory.service;

import cn.bootx.goodscenter.code.GoodsCenterCode;
import cn.bootx.goodscenter.core.inventory.manager.InventoryManager;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuManager;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.exception.inventory.InventoryNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 库存服务
 * @author xxm
 * @date 2020/11/21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final GoodsSkuManager skuManager;
    private final InventoryManager inventoryManager;

    /**
     * 获取指定 SKU 的可用库存
     */
    public Integer getAvailable(Long skuId){
        GoodsSku sku = skuManager.findById(skuId).orElseThrow(InventoryNotFoundException::new);

        // 无限库存
        if (sku.isUnlimited()) {
            return GoodsCenterCode.CAPACITY_UNLIMITED;
        }

        // 打包品与非打包库存
        if (sku.isPacking()){
            return this.getMinAvailable4Packing(skuId);
        } else {
            return sku.getAvailable();
        }
    }

    /**
     * 查询打包品的指定SKU关联的所有被打包商品的SKU 可用库存最小值
     */
    public Integer getMinAvailable4Packing(Long skuId) {
        return inventoryManager.getMinAvailable4Packing(skuId);
    }

}
