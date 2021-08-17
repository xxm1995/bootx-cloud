package cn.bootx.noticecenter.core.dingtalk.robot.dao;

import cn.bootx.noticecenter.core.dingtalk.robot.entity.DingRobotConfig;
import cn.bootx.common.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* 钉钉机器人
* @author xxm
* @date 2020/11/29
*/
@Repository
@RequiredArgsConstructor
public class DingRobotConfigManage {
    private final DingRobotConfigRepository configRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;


    public List<DingRobotConfig> findAll() {
        return configRepository.findAllByTid(headerHolder.findTid());
    }

    public Optional<DingRobotConfig> findById(Long id) {
        return configRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public Optional<DingRobotConfig> findByCode(String code) {
        return configRepository.findByCodeAndTid(code,headerHolder.findTid());
    }

    public boolean existsByCode(String code) {
        return configRepository.existsByCodeAndTid(code,headerHolder.findTid());
    }

    public void deleteById(Long id) {
        configRepository.deleteByIdAndTid(id,headerHolder.findTid());
    }
}
