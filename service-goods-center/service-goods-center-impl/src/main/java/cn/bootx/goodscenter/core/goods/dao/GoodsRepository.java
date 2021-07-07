package cn.bootx.goodscenter.core.goods.dao;

import cn.bootx.goodscenter.core.goods.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GoodsRepository extends JpaRepository<Goods,Long> {
    Optional<Goods> findByIdAndTid(Long id, Long tid);

    boolean existsByNameAndTid(String name, Long tid);

    boolean existsByCodeAndTid(String code, Long tid);

    List<Goods> findAllByTid(Long tid);

    List<Goods> findAllByCidAndTid(Long cid, Long tid);

    List<Goods> findAllByIdInAndTid(Collection<Long> goodsIds, Long tid);

    void deleteByIdInAndTid(List<Long> deleteIds, Long tid);

    @Query("select min (price) from GoodsSku where goodsId = :goodsId and tid = :tid")
    BigDecimal getGoodsLowerPriceAndTid(@Param("goodsId") Long goodsId,@Param("tid") Long tid);

    @Query("select max (price) from GoodsSku where goodsId = :goodsId and tid = :tid")
    BigDecimal getGoodsUpperPriceAndTid(@Param("goodsId") Long goodsId,@Param("tid") Long tid);
}
