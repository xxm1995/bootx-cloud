package cn.bootx.salescenter.core.coupon.dao;


import cn.bootx.salescenter.core.coupon.entity.CouponTemplate;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**   
* 优惠券策略
* @author xxm  
* @date 2020/10/19 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class CouponTemplateManager {
    private final CouponTemplateRepository couponTemplateRepository;
    private final HeaderHolder headerHolder;

    /**
     * 查询单个
     */
    public Optional<CouponTemplate> findById(Long templateId){
        return couponTemplateRepository.findByIdAndTid(templateId,headerHolder.findTid());
    }

    /**
     * 根据ids查询
     */
    public List<CouponTemplate> findAllByIds(List<Long> ids){
        return couponTemplateRepository.findAllByIdInAndTid(ids,headerHolder.findTid());
    }

    /**
     * 查询全部
     */
    public List<CouponTemplate> findAll(){
        return couponTemplateRepository.findAllByTid(headerHolder.findTid());
    }

    /**
     * 减少优惠券个数
     */
    public boolean reduceCoupons(Long couponTemplateId,int num){
        return couponTemplateRepository.reduceCoupons(couponTemplateId,num,headerHolder.findTid())>0;
    }
}
