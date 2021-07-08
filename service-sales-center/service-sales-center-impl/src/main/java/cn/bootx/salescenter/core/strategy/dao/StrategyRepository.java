package cn.bootx.salescenter.core.strategy.dao;

import cn.bootx.salescenter.core.strategy.entity.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface StrategyRepository extends JpaRepository<Strategy,Long> {

    List<Strategy> findAllByIdIn(Collection<Long> strategyIds);

    boolean existsByIdAndTid(Long id,Long tid);

    List<Strategy> findAllByTid(Long tid);

    List<Strategy> findAllByIdInAndTid(List<Long> ids, Long tid);

    boolean existsByCodeAndTid(String code, Long tid);

    boolean existsByCodeAndIdNotAndTid(String code, Long id, Long tid);
}
