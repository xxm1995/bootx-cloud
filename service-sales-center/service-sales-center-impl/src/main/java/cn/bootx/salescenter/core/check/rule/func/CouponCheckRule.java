package cn.bootx.salescenter.core.check.rule.func;

import cn.bootx.salescenter.core.calculate.cache.CalculateCache;
import cn.bootx.salescenter.core.calculate.cache.OrderCache;
import cn.bootx.salescenter.core.check.config.entity.CheckRuleConfig;
import cn.bootx.salescenter.core.check.convert.OrderConvert;
import cn.bootx.salescenter.core.coupon.entity.CouponTemplate;
import cn.bootx.salescenter.dto.check.CheckRuleResult;
import cn.bootx.salescenter.param.order.OrderCheckParam;

/**
* 优惠券使用检查接口
* @author xxm  
* @date 2020/12/6 
*/
public interface CouponCheckRule extends AbstractCheckRule {

    /**
     * 计算时检查
     */
    default CheckRuleResult check(CheckRuleConfig checkRule, CouponTemplate template, OrderCache orderCache, CalculateCache calculateCache) {
        return check(checkRule,template, OrderConvert.INSTANCE.convert(orderCache));
    }

    /**
     * 选择时检查
     */
    CheckRuleResult check(CheckRuleConfig checkRule, CouponTemplate template, OrderCheckParam orderCheckParam);
}
