package cn.bootx.goodscenter.core.sku.dao;

import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**   
*  商品sku
* @author xxm  
* @date 2020/11/20 
*/
@Repository
@RequiredArgsConstructor
public class GoodsSkuManager {
    private final GoodsSkuRepository goodsSkuRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public Optional<GoodsSku> findById(Long id) {
        return goodsSkuRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public List<GoodsSku> findByGoodsId(Long goodsId) {
        return goodsSkuRepository.findAllByGoodsIdAndTid(goodsId,headerHolder.findTid());
    }

    public List<GoodsSku> findByIds(List<Long> skuIds) {
        return goodsSkuRepository.findByIdInAndTid(skuIds,headerHolder.findTid());
    }


}
