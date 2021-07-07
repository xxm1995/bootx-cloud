package cn.bootx.goodscenter.core.sku.dao;

import cn.bootx.goodscenter.core.sku.entity.GoodsSkuAttr;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* sku属性
* @author xxm
* @date 2020/11/21
*/
@Repository
@RequiredArgsConstructor
public class GoodsSkuAttrManager {
    private final GoodsSkuAttrRepository skuAttrRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public List<GoodsSkuAttr> findBySkuId(Long skuId) {
        return skuAttrRepository.findBySkuIdAndTid(skuId,headerHolder.findTid());
    }

    /**
     * 根据skuIds删除
     */
    public void deleteBySkuIds(List<Long> skuIds) {
        skuAttrRepository.deleteBySkuIdInAndTid(skuIds,headerHolder.findTid());
    }
}
