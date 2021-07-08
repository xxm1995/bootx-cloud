package cn.bootx.salescenter.client;

import cn.bootx.salescenter.dto.coupon.CouponDto;
import cn.bootx.salescenter.param.coupon.ObtainCouponParam;

import java.util.List;

/**   
* 优惠券
* @author xxm  
* @date 2020/11/26 
*/
public interface CouponClient {
    /**
     * 领取优惠券
     */
    void obtainCoupon(ObtainCouponParam param);

    /**
     * 个人可用的优惠券
     */
    List<CouponDto> findByUser(Long userId);


    /**
     * 批量查询优惠券
     */
    List<CouponDto> findByIds(List<Long> ids);

    /**
     * 查询优惠券
     */
    CouponDto getById(Long id);

    /**
     * 锁定优惠券
     */
    void lockByIds(List<Long> ids);

    /**
     * 锁定优惠券
     */
    void lockById(Long id);
}
