package cn.bootx.salescenter.core.check.rule.impl.coupon.select;

import cn.bootx.salescenter.code.CheckRuleCode;
import cn.bootx.salescenter.core.check.config.entity.CheckRuleConfig;
import cn.bootx.salescenter.core.check.rule.func.CouponCheckRule;
import cn.bootx.salescenter.core.coupon.entity.CouponTemplate;
import cn.bootx.salescenter.dto.check.CheckRuleResult;
import cn.bootx.salescenter.param.order.OrderCheckParam;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* 优惠券渠道检查
* @author xxm
* @date 2021/5/21
*/
@Component
public class ChannelCouponSelectRule implements CouponCheckRule {
    @Override
    public String getCode() {
        return CheckRuleCode.Coupon.CHECK_CHANNEL;
    }

    @Override
    public CheckRuleResult check(CheckRuleConfig checkRule, CouponTemplate template, OrderCheckParam orderCheckParam) {
        String addition = checkRule.getAddition();
        List<String> channelIds = StrUtil.split(addition, ',');
        boolean contains = channelIds.contains(String.valueOf(orderCheckParam.getChannelId()));
        CheckRuleResult checkRuleResult = new CheckRuleResult()
                .setPass(contains);
        if (!contains){
            checkRuleResult.setMsg("渠道检查未通过");
        }
        return checkRuleResult;
    }
}
