package cn.bootx.salescenter.core.strategy.dao;

import cn.bootx.salescenter.core.strategy.entity.StrategyRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author xxm
 * @date 2020/10/31
 */
public interface StrategyRegisterRepository extends JpaRepository<StrategyRegister,Long> {

    Optional<StrategyRegister> findByIdAndTid(Long id, Long tid);

    /**
     * 按策略计数
     */
    int countByStrategyId(Long strategyId);

    /**
     * 根据策略Id删除
     */
    boolean deleteByStrategyId(Long strategyId);

    List<StrategyRegister> findAllByTid(Long tid);

    boolean existsByIdAndTid(Long id, Long tid);

    List<StrategyRegister> findAllByIdInAndTid(List<Long> ids,Long tid);

}
