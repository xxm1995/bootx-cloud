package cn.bootx.goodscenter.core.goods.dao;

import cn.bootx.goodscenter.core.goods.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods,Long> {
    Optional<Goods> findByIdAndTid(Long id, Long tid);


    List<Goods> findAllByTid(Long tid);

    List<Goods> findAllByCidAndTid(Long cid, Long tid);

    List<Goods> findAllByIdInAndTid(Collection<Long> goodsIds, Long tid);
}
