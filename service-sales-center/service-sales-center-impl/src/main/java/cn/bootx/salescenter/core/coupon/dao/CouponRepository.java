package cn.bootx.salescenter.core.coupon.dao;

import cn.bootx.salescenter.core.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    List<Coupon> findByUserIdAndTid(Long userId,Long tid);

    List<Coupon> findAllByIdInAndTid(List<Long> ids, Long tid);

    List<Coupon> findAllByUserIdAndStatusAndTid(Long userId, int state, Long tid);

    List<Coupon> findAllByUserIdAndTemplateIdAndTid(Long userId,Long templateId,Long tid);

    Optional<Coupon> findByIdAndTid(Long id, Long tid);
}
