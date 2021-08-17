package cn.bootx.salescenter.core.check.config.dao;

import cn.bootx.salescenter.code.CheckRuleCode;
import cn.bootx.salescenter.core.check.config.entity.CheckRuleConfig;
import cn.bootx.common.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CheckRuleManager {
    private final CheckRuleRepository ruleRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    /**
     * 优惠券规则
     * @see CheckRuleCode
     */
    public List<CheckRuleConfig> findByTemplates(List<Long> ids, int ruleType){
        return ruleRepository.findAllByStrategyRegisterIdInAndRegisterTypeAndRuleTypeAndTid(ids,
                CheckRuleCode.RULE_COUPON,ruleType,headerHolder.findTid());
    }

    /**
     * 优惠券规则
     * @see CheckRuleCode
     */
    public List<CheckRuleConfig> findByRegisters(List<Long> ids, int ruleType){
        return ruleRepository.findAllByStrategyRegisterIdInAndRegisterTypeAndRuleTypeAndTid(ids,
                CheckRuleCode.RULE_ACTIVITY,ruleType,headerHolder.findTid());
    }
}
