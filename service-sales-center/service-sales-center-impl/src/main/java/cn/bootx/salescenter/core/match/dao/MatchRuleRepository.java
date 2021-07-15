package cn.bootx.salescenter.core.match.dao;

import cn.bootx.salescenter.core.match.entity.MatchRuleConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRuleRepository extends JpaRepository<MatchRuleConfig,Long> {
    List<MatchRuleConfig> findAllByRegisterTypeAndStrategyRegisterIdInAndTid(int registerType, List<Long> strategyRegisterIds, Long tid);

    List<MatchRuleConfig> findAllByStrategyRegisterIdInAndTid(List<Long> strategyRegisterIds, Long tid);

    List<MatchRuleConfig> findAllByRegisterTypeAndStrategyRegisterIdAndTid(int registerType, Long strategyRegisterId, Long tid);

    List<MatchRuleConfig> findAllByStrategyRegisterIdAndTid(Long strategyRegisterId, Long tid);

}
