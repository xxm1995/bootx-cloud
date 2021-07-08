package cn.bootx.salescenter.core.check.rule.func;

import cn.bootx.salescenter.core.check.config.entity.CheckRuleConfig;
import cn.bootx.salescenter.core.coupon.entity.CouponTemplate;
import cn.bootx.salescenter.dto.check.CheckRuleResult;
import cn.bootx.salescenter.param.coupon.ObtainCouponParam;

/**
* 优惠券领取检查接口
* @author xxm  
* @date 2020/12/6 
*/
public interface CouponObtainRule extends AbstractCheckRule {

    /**
     * 领取检查
     */
    CheckRuleResult check(CheckRuleConfig checkRule, CouponTemplate couponTemplate, ObtainCouponParam param);
}
