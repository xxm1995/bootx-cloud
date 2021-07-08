package cn.bootx.salescenter.core.strategy.dao;

import cn.bootx.salescenter.core.strategy.entity.StrategyConfigValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StrategyConfigValueRepository extends JpaRepository<StrategyConfigValue,Long> {
    /**
     * 根据策略id删除
     */
    void deleteByStrategyId(Long strategyId);

    List<StrategyConfigValue> findByStrategyIdAndTid(Long strategyId,Long tid);

    List<StrategyConfigValue> findByStrategyRegisterIdAndTid(Long id,Long tid);

    void deleteByStrategyRegisterId(Long strategyRegisterId);

    List<StrategyConfigValue> findAllByStrategyRegisterIdInAndTid(List<Long> ids, Long tid);
}
