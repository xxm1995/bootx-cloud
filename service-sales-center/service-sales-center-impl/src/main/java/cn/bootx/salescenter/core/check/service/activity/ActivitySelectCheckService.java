package cn.bootx.salescenter.core.check.service.activity;

import cn.bootx.salescenter.core.activity.entity.Activity;
import cn.bootx.salescenter.core.check.config.entity.CheckRuleConfig;
import cn.bootx.salescenter.core.check.rule.CheckRuleHandlerAdapter;
import cn.bootx.salescenter.dto.check.CheckRuleResult;
import cn.bootx.salescenter.param.order.OrderCheckParam;
import cn.bootx.salescenter.param.order.OrderDetailCheckParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**   
* 活动选择时检查
* @author xxm  
* @date 2021/5/14 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ActivitySelectCheckService {
    private final CheckRuleHandlerAdapter checkRuleHandlerAdapter;

    /**
     * 活动选择检查
     */
    public List<Activity> activitiesCheck(OrderDetailCheckParam detail, OrderCheckParam orderParam, List<Activity> activities) {
        List<Activity> selectActivities = new ArrayList<>();
        out:for (Activity activity : activities) {
            List<CheckRuleConfig> checkRules = Optional.ofNullable(activity.getCheckRules())
                    .orElse(new ArrayList<>(0));
            // 排序
            checkRules.sort(CheckRuleConfig::compareTo);
            for (CheckRuleConfig checkRule : checkRules) {
                // 不通过
                CheckRuleResult checkRuleResult = checkRuleHandlerAdapter.activitySelectCheck(checkRule, activity, detail, orderParam);
                if (!checkRuleResult.isPass()){
                    continue out;
                }
            }
            selectActivities.add(activity);
        }
        return selectActivities;
    }
}
