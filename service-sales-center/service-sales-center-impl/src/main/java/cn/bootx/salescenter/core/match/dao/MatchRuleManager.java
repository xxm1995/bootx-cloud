package cn.bootx.salescenter.core.match.dao;

import cn.bootx.salescenter.code.MatchTypeCode;
import cn.bootx.salescenter.core.match.entity.MatchRuleConfig;
import cn.bootx.salescenter.core.match.entity.QMatchRuleConfig;
import cn.bootx.salescenter.dto.match.FeaturePoints;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cn.bootx.salescenter.code.MatchRuleCode.MATCH_ACTIVITY;

/**
 * 匹配规则
 * @author xxm
 * @date 2021/5/8
 */
@Repository
@RequiredArgsConstructor
public class MatchRuleManager {
    private final MatchRuleRepository checkMatchRepository;
    private final MatchRuleConfigMapper matchRuleConfigMapper;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public List<MatchRuleConfig> findByStrategyRegisterIds(List<Long> templateIds) {
        return checkMatchRepository.findAllByStrategyRegisterIdInAndTid(templateIds,headerHolder.findTid());
    }

    public List<MatchRuleConfig> findByStrategyRegisterId(Long strategyRegisterId){
        return checkMatchRepository.findAllByStrategyRegisterIdAndTid(strategyRegisterId,headerHolder.findTid());
    }


    /**
     * 全局生效的活动策略(非eq类型,)
     */
    public List<MatchRuleConfig> findGlobalActivityMatch(){
        QMatchRuleConfig q = QMatchRuleConfig.matchRuleConfig;
        return jpaQueryFactory.selectFrom(q)
                .where(
                        q.registerType.eq(MATCH_ACTIVITY),
                        q.matchType.ne(MatchTypeCode.EQ)
                ).fetch();
    }

    /**
     * 查询匹配(eq类型)
     */
    public List<MatchRuleConfig> findByActivityMatch(List<FeaturePoints> featurePoints) {

        LambdaQueryWrapper<MatchRuleConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MatchRuleConfig::getRegisterType, MATCH_ACTIVITY)
                .eq(MatchRuleConfig::getMatchType, MatchTypeCode.EQ)
                // 特征点
                .and(queryWrapper ->{
                    for (FeaturePoints feature : featurePoints) {
                        queryWrapper.or(o ->
                                o.eq(MatchRuleConfig::getFeatureType,feature.getType()).in(MatchRuleConfig::getFeaturePoint,feature.getPoints()));
                    }
                });


        return matchRuleConfigMapper.selectList(wrapper);
    }
}
