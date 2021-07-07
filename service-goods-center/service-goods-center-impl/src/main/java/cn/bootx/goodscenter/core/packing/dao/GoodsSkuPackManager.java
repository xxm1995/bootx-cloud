package cn.bootx.goodscenter.core.packing.dao;

import cn.bootx.goodscenter.core.packing.entity.GoodsSkuPack;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**   
* sku打包
* @author xxm  
* @date 2020/11/22 
*/
@Repository
@RequiredArgsConstructor
public class GoodsSkuPackManager {
    private final GoodsSkuPackRepository skuPackingRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public void deleteBySkuId(Long skuId) {
        skuPackingRepository.deleteByGoodsSkuIdAndTid(skuId,headerHolder.findTid());
    }

    public void deleteBySkuIds(List<Long> skuIds) {
        skuPackingRepository.deleteByGoodsSkuIdInAndTid(skuIds,headerHolder.findTid());
    }

    public List<GoodsSkuPack> findBySkuId(Long skuId) {
        return skuPackingRepository.findAllByGoodsSkuIdAndTid(skuId,headerHolder.findTid());
    }
}
