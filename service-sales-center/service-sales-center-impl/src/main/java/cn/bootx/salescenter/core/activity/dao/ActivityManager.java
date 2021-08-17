package cn.bootx.salescenter.core.activity.dao;

import cn.bootx.salescenter.core.activity.entity.Activity;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**   
* 优惠活动
* @author xxm  
* @date 2021/5/7 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class ActivityManager {
    private final ActivityRepository activityRepository;


    /**
     * 根据ids查询
     */
    public List<Activity> findByIds(List<Long> ids){
        return activityRepository.findAllByIdInAndTid(ids,headerHolder.findTid());
    }

    /**
     * 根据策略注册ids查询
     */
    public List<Activity> findByStrategyRegister(List<Long> strategyRegisterIds){
        return activityRepository.findAllByStrategyRegisterIdInAndTid(strategyRegisterIds,headerHolder.findTid());
    }
}
