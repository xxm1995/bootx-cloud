package cn.bootx.goodscenter.core.sku.dao;

import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GoodsSkuRepository extends JpaRepository<GoodsSku,Long> {
    Optional<GoodsSku> findByIdAndTid(Long id, Long tid);

    List<GoodsSku> findAllByGoodsIdAndTid(Long goodsId, Long tid);


    List<GoodsSku> findByIdInAndTid(List<Long> skuIds, Long tid);
}
