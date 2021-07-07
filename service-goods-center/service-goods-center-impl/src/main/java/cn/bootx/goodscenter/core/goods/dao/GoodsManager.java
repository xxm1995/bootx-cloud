package cn.bootx.goodscenter.core.goods.dao;

import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.hutool.core.collection.CollUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
* 商品
* @author xxm
* @date 2020/11/20
*/
@Repository
@RequiredArgsConstructor
public class GoodsManager {
    private final GoodsRepository goodsRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public Optional<Goods> findById(Long id){
        return goodsRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public boolean existsName(String name) {
        return goodsRepository.existsByNameAndTid(name,headerHolder.findTid());
    }

    public boolean existsCode(String code) {
        return goodsRepository.existsByCodeAndTid(code,headerHolder.findTid());
    }

    public List<Goods> findAll() {
        return goodsRepository.findAllByTid(headerHolder.findTid());
    }

    public List<Goods> findByCid(Long cid) {
        return goodsRepository.findAllByCidAndTid(cid,headerHolder.findTid());
    }

    public List<Goods> findByIds(Collection<Long> goodsIds) {
        return goodsRepository.findAllByIdInAndTid(goodsIds,headerHolder.findTid());
    }

    public void deleteByIds(List<Long> deleteIds) {
        if (CollUtil.isNotEmpty(deleteIds)){
            goodsRepository.deleteByIdInAndTid(deleteIds,headerHolder.findTid());
        }
    }

    /**
     * 价格下限
     */
    public BigDecimal getGoodsLowerPrice(Long goodsId) {
        return goodsRepository.getGoodsLowerPriceAndTid(goodsId,headerHolder.findTid());
    }

    /**
     * 价格上限
     */
    public BigDecimal getGoodsUpperPrice(Long goodsId) {
        return goodsRepository.getGoodsUpperPriceAndTid(goodsId,headerHolder.findTid());
    }
}
