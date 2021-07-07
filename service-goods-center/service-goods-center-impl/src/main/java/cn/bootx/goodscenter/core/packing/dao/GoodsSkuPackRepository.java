package cn.bootx.goodscenter.core.packing.dao;

import cn.bootx.goodscenter.core.packing.entity.GoodsSkuPack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodsSkuPackRepository extends JpaRepository<GoodsSkuPack,Long> {
    void deleteByGoodsSkuIdAndTid(Long skuId, Long tid);
    void deleteByGoodsSkuIdInAndTid(List<Long> skuId, Long tid);

    List<GoodsSkuPack> findAllByGoodsSkuIdAndTid(Long skuId, Long tid);

    Optional<GoodsSkuPack> findSkuByPackedSkuIdAndTid(Long packedSkuId, Long tid);
}
