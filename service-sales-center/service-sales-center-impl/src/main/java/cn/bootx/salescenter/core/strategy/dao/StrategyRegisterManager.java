package cn.bootx.salescenter.core.strategy.dao;

import cn.bootx.salescenter.core.strategy.entity.StrategyRegister;
import cn.bootx.common.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class StrategyRegisterManager {
    private final StrategyRegisterRepository strategyRegisterRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final HeaderHolder headerHolder;


    /**
     * 查询
     */
    public Optional<StrategyRegister> findById(Long id){
        return strategyRegisterRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    /**
     * 根据SubjectType和SubjectId查询 开启状态的 注册策略
     */
//    public List<StrategyRegister> findAllBySubjectTypeAndSubjectId(int subjectType, Long subjectId) {
//        return strategyRegisterRepository
//                .findAllBySubjectTypeAndSubjectIdAndActiveAndTid(subjectType, subjectId, StrategyRegisterCode.ACTIVE_YES,headerHolder.findTid());
//    }

    /**
     * 根据SubjectType和SubjectIds查询 开启状态的 注册策略
     */
//    public List<StrategyRegister> findAllBySubjectTypeAndSubjectIds(int subjectType, List<Long> subjectIds) {
//        QStrategyRegister q = QStrategyRegister.strategyRegister;
//        return jpaQueryFactory.selectFrom(q)
//                .where(
//                        q.tid.eq(headerHolder.findTid()),
//                        q.subjectType.eq(subjectType),
//                        q.subjectId.in(subjectIds),
//                        q.active.eq(StrategyRegisterCode.ACTIVE_YES),
//                        q.state.eq(StrategyRegisterCode.STATE_NORMAL)
//                ).fetch();
//    }

    /**
     * 查询全部
     */
    public List<StrategyRegister> findAll() {
        return strategyRegisterRepository.findAllByTid(headerHolder.getTid());
    }

    /**
     * 策略注册是否存在
     */
    public boolean existsById(Long id){
        return strategyRegisterRepository.existsByIdAndTid(id,headerHolder.findTid());
    }

    /**
     * 根据ids查询
     */
    public List<StrategyRegister> findByIds(List<Long> ids){
        return strategyRegisterRepository.findAllByIdInAndTid(ids,headerHolder.findTid());
    }
}
