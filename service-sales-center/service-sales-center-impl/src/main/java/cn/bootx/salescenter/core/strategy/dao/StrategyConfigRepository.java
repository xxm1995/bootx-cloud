package cn.bootx.salescenter.core.strategy.dao;

import cn.bootx.salescenter.core.strategy.entity.StrategyConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StrategyConfigRepository extends JpaRepository<StrategyConfig,Long> {
    /**
     * 查询策略配置
     */
    List<StrategyConfig> findByStrategyId(Long strategyId);

    /**
     * 根据策略id进行删除
     */
    void deleteByStrategyId(Long strategyId);

    /**
     * 根据类型查询
     */
    List<StrategyConfig> findByType(Integer type);
}
