package cn.bootx.salescenter.core.strategy.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.common.core.util.ResultConvertUtils;
import cn.bootx.salescenter.core.strategy.dao.*;
import cn.bootx.salescenter.core.strategy.entity.Strategy;
import cn.bootx.salescenter.core.strategy.entity.StrategyConfig;
import cn.bootx.salescenter.dto.strategy.StrategyConfigDto;
import cn.bootx.salescenter.dto.strategy.StrategyDto;
import cn.bootx.salescenter.exception.strategy.StrategyAlreadyUsedException;
import cn.bootx.salescenter.exception.strategy.StrategyNotExistException;
import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 策略
 * @author xxm
 * @date 2020/10/11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StrategyService {

    private final StrategyManager strategyManager;

    private final StrategyRepository strategyRepository;
    private final StrategyConfigRepository strategyConfigRepository;
    private final StrategyRegisterRepository strategyRegisterRepository;
    private final StrategyConfigValueRepository strategyConfigValueRepository;

    /**
     * 添加策略及其配置项
     */
    public StrategyDto addStrategy(StrategyDto param){
        Strategy strategy = Strategy.init(param);
        // 检查code是否重复
        if (strategyManager.existsByCode(param.getCode())){
            throw new BizException("策略code重复");
        }
        Strategy save = strategyRepository.save(strategy);
        this.strategyConfigsPersistence(save.getId(), param.getConfigParams());
        return save.toDto();
    }

    /**
     * 查询所有的策略
     */
    public List<StrategyDto> findAll() {
        List<Strategy> strategyList = strategyManager.findAll();
        if (CollectionUtil.isEmpty(strategyList)) {
            return new ArrayList<>();
        }
        return strategyList.stream()
                .map(strategy -> {
                    StrategyDto dto = strategy.toDto();
                    dto.setRuleScript(null);
                    return dto;
                }).collect(Collectors.toList());
    }

    /**
     * 更新策略及其配置项 如果已被注册则不允许更改  配置项及脚本程序
     */
    @Transactional(rollbackFor = Exception.class)
    public StrategyDto update(StrategyDto strategyDto){

        Long strategyId = strategyDto.getId();

        if (strategyRegisterRepository.countByStrategyId(strategyId) == 0){
            Strategy strategy = Strategy.init(strategyDto);
            //删除原有配置项
            strategyRegisterRepository.deleteByStrategyId(strategyId);
            //如果存在配置项 则持久化配置项
            this.strategyConfigsPersistence(strategyId, strategyDto.getConfigParams());

            Strategy save = strategyRepository.save(strategy);

            return save.toDto();
        } else {
            throw new StrategyAlreadyUsedException();
        }
    }

    /**
     * 策略配置持久性
     */
    private void strategyConfigsPersistence(Long strategyId, List<StrategyConfigDto> configParams){
        if (!CollectionUtil.isEmpty(configParams)){
            List<StrategyConfig> strategyConfigs = new ArrayList<>();
            configParams.forEach(strategyConfigDto -> {
                StrategyConfig strategyConfig = StrategyConfig.init(strategyConfigDto);
                strategyConfig.setStrategyId(strategyId);
                strategyConfigs.add(strategyConfig);
            });

            strategyConfigRepository.saveAll(strategyConfigs);
        }
    }
    /**
     * 删除策略及其配置项
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long strategyId){
        try {
            strategyRepository.deleteById(strategyId);
            //删除原有配置项
            strategyConfigRepository.deleteByStrategyId(strategyId);

            // 策略注册
            strategyRegisterRepository.deleteByStrategyId(strategyId);

            // 策略脚本配置项
            strategyConfigValueRepository.deleteByStrategyId(strategyId);
        }catch (EmptyResultDataAccessException e) {
            log.info("策略不存在，策略ID为：{}", strategyId);
            throw new StrategyNotExistException();
        }
    }

    /**
     * 获取策略及其配置项
     */
    public StrategyDto getById(Long strategyId){
        StrategyDto strategy = strategyRepository.findById(strategyId)
                .map(Strategy::toDto)
                .orElseThrow(StrategyNotExistException::new);

        List<StrategyConfig> strategyConfigs = strategyConfigRepository.findByStrategyId(strategyId);
        List<StrategyConfigDto> strategyConfigDtoList = strategyConfigs.stream()
                .map(StrategyConfig::toDto)
                .collect(Collectors.toList());

        strategy.setConfigParams(strategyConfigDtoList);

        // 是否被注册使用
        if (strategyRegisterRepository.countByStrategyId(strategyId) == 0){
            strategy.setUpdatable(true);
        }
        return strategy;
    }

    /**
     * 通过策略ID获取配置
     */
    public List<StrategyConfigDto> findConfigsByStrategyId(Long strategyId) {
        List<StrategyConfig> strategyConfigs = strategyConfigRepository.findByStrategyId(strategyId);
        List<StrategyConfigDto> strategyConfigDtoList = new ArrayList<>();
        if(!CollectionUtil.isEmpty(strategyConfigs)) {
            strategyConfigs.forEach(strategyConfig -> strategyConfigDtoList.add(strategyConfig.toDto()));
        }
        return strategyConfigDtoList;
    }

    /**
     * 根据类型查询
     */
    public List<StrategyConfigDto> findByType(Integer type) {
        return ResultConvertUtils.dtoListConvert(strategyConfigRepository.findByType(type));
    }
}
