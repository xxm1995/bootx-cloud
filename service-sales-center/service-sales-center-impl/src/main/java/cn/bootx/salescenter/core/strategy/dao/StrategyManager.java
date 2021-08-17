package cn.bootx.salescenter.core.strategy.dao;

import cn.bootx.salescenter.core.strategy.entity.Strategy;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 策略
* @author xxm
* @date 2020/10/11
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class StrategyManager {

    private final HeaderHolder headerHolder;
    private final StrategyRepository strategyRepository;


    public List<Strategy> findAll() {
        return strategyRepository.findAllByTid(headerHolder.findTid());
    }

    public boolean existsById(Long id){
        return strategyRepository.existsByIdAndTid(id,headerHolder.findTid());
    }

    public boolean existsByCode(String code){
        return strategyRepository.existsByCodeAndTid(code,headerHolder.findTid());
    }

    public boolean existsByCode(String code,Long id){
        return strategyRepository.existsByCodeAndIdNotAndTid(code,id,headerHolder.findTid());
    }

    public List<Strategy> findAllByIds(List<Long> ids){
        return strategyRepository.findAllByIdInAndTid(ids,headerHolder.findTid());
    }
}
