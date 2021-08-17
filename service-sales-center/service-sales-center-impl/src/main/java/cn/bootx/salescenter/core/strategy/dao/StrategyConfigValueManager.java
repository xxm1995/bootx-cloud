package cn.bootx.salescenter.core.strategy.dao;

import cn.bootx.salescenter.core.strategy.entity.StrategyConfigValue;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StrategyConfigValueManager {
    private final StrategyConfigValueRepository strategyConfigValueRepository;
    private final StrategyRepository strategyRepository;
    private final StrategyRegisterRepository strategyRegisterRepository;

    private final HeaderHolder headerHolder;


    /**
     * 根据注册id获取配置参数
     */
    public List<StrategyConfigValue> getByStrategyRegisterId(Long id) {
        return strategyConfigValueRepository.findByStrategyRegisterIdAndTid(id,headerHolder.getTid());
    }

    /**
     * 根据注册ids获取配置参数
     */
    public List<StrategyConfigValue> findAllStrategyRegisterIds(List<Long> ids) {
        return strategyConfigValueRepository.findAllByStrategyRegisterIdInAndTid(ids,headerHolder.getTid());
    }

    /**
     * 获取订单类型的策略的配置参数
     */
    @Deprecated
    public List<StrategyConfigValue> getOrderTypeStrategyConfigValue(Long strategyId) {
        return strategyConfigValueRepository.findByStrategyIdAndTid(strategyId,headerHolder.getTid());
    }
}
