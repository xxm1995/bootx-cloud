package cn.bootx.salescenter.core.coupon.dao;

import cn.bootx.salescenter.core.coupon.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CouponTemplateRepository extends JpaRepository<CouponTemplate,Long> {

    Optional<CouponTemplate> findByIdAndTid(Long id,Long tid);

    List<CouponTemplate> findAllByTid(Long tid);

    List<CouponTemplate> findAllByIdInAndTid(List<Long> ids, Long tid);

    @Modifying
    @Query("update CouponTemplate " +
            "set num = (num-:count),version = (version + 1) " +
            "where id = :couponTemplateId and (num - :count) >= 0 and tid = :tid")
    int reduceCoupons(@Param("couponTemplateId") Long couponTemplateId, @Param("count")int count, @Param("tid")Long tid);
}
