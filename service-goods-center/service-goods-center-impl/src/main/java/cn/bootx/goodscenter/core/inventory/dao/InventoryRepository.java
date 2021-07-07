package cn.bootx.goodscenter.core.inventory.dao;

import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
* 库存操作 数据库
* @author xxm
* @date 2020/11/23
*/
public interface InventoryRepository extends JpaRepository<GoodsSku, Long> {

    /**
     * 查询打包品的指定SKU关联的所有被打包商品的SKU 可用库存最小值
     */
    @Query("SELECT min(sku.available) FROM GoodsSku sku" +
            " WHERE sku.id IN (" +
            "  SELECT p.packedSkuId FROM GoodsSkuPack p WHERE p.goodsSkuId = :skuId AND p.tid = :tid" +
            ")")
    Integer getMinAvailable4Packing(@Param("skuId") Long skuId,@Param("tid")Long tid);

    /**
     * 锁定库存
     */
    @Modifying
    @Query(" update GoodsSku " +
            " set available = (available - :count), locked = (locked + :count), version = (version + 1) " +
            " where id = :id and (available - :count) >= 0")
    int lockInventory(@Param("id") Long id, @Param("count") int count);

    /**
     * 解锁库存
     */
    @Modifying
    @Query(" update GoodsSku " +
            " set available = (available + :count), locked = (locked - :count), version = (version + 1) " +
            " where id = :id and (locked - :count) >= 0")
    int unlockInventory(@Param("id") Long id, @Param("count") int count);

    /**
     * 减少库存
     */
    @Modifying
    @Query(" update GoodsSku " +
            " set locked = (locked - :count), sold = (sold + :count), version = (version + 1) " +
            " where id = :id and (locked - :count) >= 0")
    int reduceInventory(@Param("id") Long id, @Param("count") int count);

    /**
     * 增补指定 SKU 的可售库存， 扣减对应售出
     */
    @Modifying
    @Query(" update GoodsSku " +
            " set available = (available + :count), sold = (sold - :count), version = (version + 1) " +
            " where id = :id")
    int increaseInventory(@Param("id") Long id, @Param("count") int count);

    /**
     * 增补指定 SKU 的可售库存
     */
    @Modifying
    @Query(" update GoodsSku " +
            " set available = (available + :count), capacity = (capacity + :count), version = (version + 1) " +
            " where id = :id")
    int increaseAvailable(@Param("id") Long id, @Param("count") int count);

    /**
     * 扣减指定 SKU 的预占库存并减少总库存
     */
    @Modifying
    @Query(" update GoodsSku " +
            " set locked = (locked - :count), capacity = (capacity - :count), version = (version + 1) " +
            " where id = :id and (locked - :count) >= 0")
    int reduceLockedAndCapacity(@Param("id") Long id, @Param("count") int count);

    /**
     * 扣减指定 SKU 的售出库存并减少总库存
     */
    @Modifying
    @Query(" update GoodsSku " +
            " set sold = (sold - :count), capacity = (capacity - :count), version = (version + 1) " +
            " where id = :id and (capacity - :count) >= 0 and (sold - :count) >= 0")
    int reduceSoldAndCapacity(@Param("id") Long id, @Param("count") int count);
}
