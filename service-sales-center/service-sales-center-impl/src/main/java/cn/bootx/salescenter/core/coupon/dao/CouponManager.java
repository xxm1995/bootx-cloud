package cn.bootx.salescenter.core.coupon.dao;

import cn.bootx.salescenter.core.coupon.entity.Coupon;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**   
* 优惠券
* @author xxm  
* @date 2020/11/2 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class CouponManager {
    private final CouponRepository couponRepository;
    private final HeaderHolder headerHolder;

    /**
     * 根据id查询
     */
    public Optional<Coupon> findById(Long id){
        return couponRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    /**
     * 根据ids查询
     */
    public List<Coupon> findByIds(List<Long> ids){
        return couponRepository.findAllByIdInAndTid(ids,headerHolder.findTid());
    }

    /**
     * 查询用户的优惠券
     */
    public List<Coupon> findByUser(Long userId){
        return couponRepository.findByUserIdAndTid(userId,headerHolder.findTid());
    }
     /**
     * 查询用户指定状态的优惠券
     */
    public List<Coupon> findByUserAndStatus(Long userId, int status){
        return couponRepository.findAllByUserIdAndStatusAndTid(userId,status,headerHolder.findTid());
    }

    /**
     * 根据优惠券模板查询用户拥有的优惠券
     */
    public List<Coupon> findByUserAndTemplate(Long userId,Long templateId) {
        return couponRepository.findAllByUserIdAndTemplateIdAndTid(userId,templateId,headerHolder.findTid());
    }
}
