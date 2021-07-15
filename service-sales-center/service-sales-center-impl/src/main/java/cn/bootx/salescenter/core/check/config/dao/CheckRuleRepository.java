package cn.bootx.salescenter.core.check.config.dao;

import cn.bootx.salescenter.core.check.config.entity.CheckRuleConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckRuleRepository extends JpaRepository<CheckRuleConfig,Long> {

    /**
     * 根据外联ID、类型和规则类型查询出规则组
     */
    List<CheckRuleConfig> findAllByStrategyRegisterIdAndRegisterTypeAndRuleTypeAndTid(Long id, int RegisterType, int ruleType, Long tid);

    List<CheckRuleConfig> findAllByStrategyRegisterIdInAndRegisterTypeAndTid(List<Long> ids, int ruleCoupon, Long tid);

    List<CheckRuleConfig> findAllByStrategyRegisterIdInAndRegisterTypeAndRuleTypeAndTid(List<Long> ids, int ruleCoupon, int checkType, Long tid);
}
