package cn.bootx.goodscenter.core.sku.dao;

import cn.bootx.goodscenter.core.sku.entity.GoodsSkuAttr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsSkuAttrRepository extends JpaRepository<GoodsSkuAttr,Long> {

    List<GoodsSkuAttr> findBySkuIdAndTid(Long skuId, Long tid);

    void deleteBySkuIdInAndTid(List<Long> skuIds,Long tid);
}
