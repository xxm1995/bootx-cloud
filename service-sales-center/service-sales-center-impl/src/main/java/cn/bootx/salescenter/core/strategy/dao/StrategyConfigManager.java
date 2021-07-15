package cn.bootx.salescenter.core.strategy.dao;

import cn.bootx.salescenter.core.strategy.entity.StrategyConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StrategyConfigManager {
    private final StrategyConfigRepository strategyConfigRepository;

    public List<StrategyConfig> findByStrategyId(Long strategyId){
        return strategyConfigRepository.findByStrategyId(strategyId);
    }
}
