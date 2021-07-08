package cn.bootx.salescenter.core.check.rule.impl.coupon.obtain;

import cn.bootx.salescenter.code.CheckRuleCode;
import cn.bootx.salescenter.code.CouponStatusCode;
import cn.bootx.salescenter.core.check.config.entity.CheckRuleConfig;
import cn.bootx.salescenter.core.check.rule.func.CouponObtainRule;
import cn.bootx.salescenter.core.coupon.dao.CouponManager;
import cn.bootx.salescenter.core.coupon.entity.Coupon;
import cn.bootx.salescenter.core.coupon.entity.CouponTemplate;
import cn.bootx.salescenter.dto.check.CheckRuleResult;
import cn.bootx.salescenter.param.coupon.ObtainCouponParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 重复领取限制
 * @author xxm
 * @date 2020/12/6
 */
@Component
@RequiredArgsConstructor
public class ObtainMultipleRuleImpl implements CouponObtainRule {
    private final CouponManager couponManager;

    @Override
    public String getCode() {
        return CheckRuleCode.Coupon.OBTAIN_MULTIPLE;
    }

    @Override
    public CheckRuleResult check(CheckRuleConfig checkRule, CouponTemplate couponTemplate, ObtainCouponParam param) {
        // 重复领取限制
        List<Coupon> coupons = couponManager.findByUserAndTemplate(param.getUserId(), param.getTemplateId());
        long count = coupons.stream()
                .filter(coupon -> !Objects.equals(coupon.getStatus(), CouponStatusCode.STATUS_EXPIRED))
                .count();
        CheckRuleResult checkRuleResult = new CheckRuleResult();
        if (count>0){
            checkRuleResult.setMsg("优惠券不可重复领取")
                    .setPass(false);
        }
        return checkRuleResult;
    }

}
