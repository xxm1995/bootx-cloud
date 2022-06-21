package cn.bootx.goodscenter.core.goods.dao;

import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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

    public List<Goods> findAll() {
        return goodsRepository.findAllByTid(headerHolder.findTid());
    }

    public List<Goods> findByCid(Long cid) {
        return goodsRepository.findAllByCidAndTid(cid,headerHolder.findTid());
    }

    public List<Goods> findByIds(Collection<Long> goodsIds) {
        return goodsRepository.findAllByIdInAndTid(goodsIds,headerHolder.findTid());
    }
}
