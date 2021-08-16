package cn.bootx.salescenter.core.strategy.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.salescenter.core.check.config.dao.CheckRuleRepository;
import cn.bootx.salescenter.core.check.config.entity.CheckRuleConfig;
import cn.bootx.salescenter.core.match.dao.MatchRuleRepository;
import cn.bootx.salescenter.core.match.entity.MatchRuleConfig;
import cn.bootx.salescenter.core.strategy.dao.*;
import cn.bootx.salescenter.core.strategy.entity.StrategyConfig;
import cn.bootx.salescenter.core.strategy.entity.StrategyConfigValue;
import cn.bootx.salescenter.core.strategy.entity.StrategyRegister;
import cn.bootx.salescenter.dto.strategy.StrategyRegisterDto;
import cn.bootx.salescenter.exception.strategy.StrategyNotExistException;
import cn.bootx.salescenter.param.strategy.CheckRuleParam;
import cn.bootx.salescenter.param.strategy.MatchRuleParam;
import cn.bootx.salescenter.param.strategy.StrategyConfigValueParam;
import cn.bootx.salescenter.param.strategy.StrategyRegisterParam;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 策略注册
 * @author xxm
 * @date 2020/10/11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StrategyRegisterService {
    private final StrategyRegisterManager strategyRegisterManager;

    private final StrategyRepository strategyRepository;
    private final StrategyRegisterRepository strategyRegisterRepository;
    private final StrategyConfigValueRepository strategyConfigValueRepository;
    private final StrategyConfigRepository strategyConfigRepository;

    private final MatchRuleRepository matchRuleRepository;
    private final CheckRuleRepository checkRuleRepository;


    /**
     * 新增策略注册
     */
    @Transactional(rollbackFor = Exception.class)
    public StrategyRegisterDto add(StrategyRegisterParam param){
        StrategyRegister strategyRegister = this.build(param);
        strategyRegister = strategyRegisterRepository.save(strategyRegister);

        // 保存匹配规则
        this.addCheckMatch(param.getMatchRules(),strategyRegister);
        // 保存检查规则
        this.addCheckRules(param.getCheckRules(),strategyRegister);
        // 保存策略参数值
        this.addConfigValue(strategyRegister.getStrategyId(),strategyRegister.getId(),param.getConfigValues());
        return strategyRegister.toDto();
    }


    /**
     * 建立策略注册
     */
    public StrategyRegister build(StrategyRegisterParam param){
        StrategyRegister strategyRegister = StrategyRegister.init(param);
        boolean strategyExist = strategyRepository.existsById(param.getStrategyId());
        if (!strategyExist){
            throw new StrategyNotExistException();
        }
        return strategyRegister;
    }

    /**
     * 添加匹配规则
     */
    private void addCheckMatch(List<MatchRuleParam> matchRules, StrategyRegister strategyRegister) {
        List<MatchRuleConfig> checkMatches = matchRules
                .stream()
                .map(rule -> MatchRuleConfig.init(rule)
                        .setRegisterType(strategyRegister.getStrategyType())
                        .setStrategyRegisterId(strategyRegister.getId())
                ).collect(Collectors.toList());
        matchRuleRepository.saveAll(checkMatches);
    }

    /**
     * 添加检查规则
     */
    private void addCheckRules(List<CheckRuleParam> checkRules, StrategyRegister strategyRegister) {
        if (CollUtil.isEmpty(checkRules)){
            return;
        }
        List<CheckRuleConfig> rules = checkRules.stream()
                .map(rule -> CheckRuleConfig.init(rule)
                        .setRegisterType(strategyRegister.getStrategyType())
                        .setStrategyRegisterId(strategyRegister.getId()))
                .collect(Collectors.toList());
        checkRuleRepository.saveAll(rules);
    }

    /**
     * 策略配置处理
     */
    private void addConfigValue(Long strategyId, Long strategyRegisterId, List<StrategyConfigValueParam> configValues) {
        if (!CollectionUtil.isEmpty(configValues)) {
            List<StrategyConfigValue> strategyConfigValues = new ArrayList<>();
            configValues.forEach(configValue -> {
                StrategyConfig strategyConfig = strategyConfigRepository.findById(configValue.getStrategyConfigId())
                        .orElseThrow(() -> new BizException("策略配置不存在"));
                StrategyConfigValue strategyConfigValue = StrategyConfigValue.init(configValue)
                        .setKey(strategyConfig.getKey())
                        .setStrategyRegisterId(strategyRegisterId)
                        .setStrategyId(strategyId);

                strategyConfigValues.add(strategyConfigValue);
            });
            strategyConfigValueRepository.saveAll(strategyConfigValues);
        }
    }

    /**
     * 查询全部策略注册信息
     */
    public List<StrategyRegisterDto> findAll(){
        List<StrategyRegister> all = strategyRegisterManager.findAll();
        return all.stream()
                .map(StrategyRegister::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 删除策略注册
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id){
        if (strategyRegisterManager.existsById(id)){
            // 删除策略
            strategyRepository.deleteById(id);
            // 删除策略配置信息
            strategyConfigValueRepository.deleteByStrategyRegisterId(id);
        }
    }
}
