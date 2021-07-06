package cn.bootx.noticecenter.core.dingtalk.robot.dao;

import cn.bootx.noticecenter.core.dingtalk.robot.entity.DingRobotConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DingRobotConfigRepository extends JpaRepository<DingRobotConfig,Long> {

    List<DingRobotConfig> findAllByTid(Long tid);

    Optional<DingRobotConfig> findByIdAndTid(Long id, Long tid);

    Optional<DingRobotConfig> findByCodeAndTid(String code, Long tid);

    boolean existsByCodeAndTid(String code, Long tid);

    boolean existsByTid(Long tid);

    void deleteByIdAndTid(Long id, Long tid);
}
