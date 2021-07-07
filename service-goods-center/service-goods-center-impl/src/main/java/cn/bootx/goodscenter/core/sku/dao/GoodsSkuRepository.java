package cn.bootx.goodscenter.core.sku.dao;

import cn.bootx.goodscenter.code.SkuSalesStateCode;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GoodsSkuRepository extends JpaRepository<GoodsSku,Long> {
    Optional<GoodsSku> findByIdAndTid(Long id, Long tid);

    List<GoodsSku> findAllByGoodsIdAndTid(Long goodsId, Long tid);

    List<GoodsSku> findByGoodsIdAndAttrValuesInAndTid(Long goodsId, List<String> attrValues,Long tid);

    List<GoodsSku> findByIdInAndTid(List<Long> skuIds, Long tid);

    @Query("update GoodsSku set saleState = "+ SkuSalesStateCode.ON +",saleOnTime = :now,version=version+1 " +
            "where goodsId =:goodsId and tid = :tid")
    @Modifying
    void saleOnByGoodsAndTid(@Param("goodsId") Long goodsId, @Param("now") LocalDateTime now, @Param("tid") Long tid);

    @Query("update GoodsSku set saleState = "+ SkuSalesStateCode.OFF_GOODS +",saleOnTime = :now,version=version+1 " +
            "where goodsId =:goodsId and tid = :tid")
    @Modifying
    void saleOffByGoodsAndTid(Long goodsId, LocalDateTime now, Long tid);
}
