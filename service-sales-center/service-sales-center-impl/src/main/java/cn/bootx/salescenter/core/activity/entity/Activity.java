package cn.bootx.salescenter.core.activity.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.salescenter.core.activity.convert.ActivityConvert;
import cn.bootx.salescenter.core.check.config.entity.CheckRuleConfig;
import cn.bootx.salescenter.core.match.entity.MatchRuleConfig;
import cn.bootx.salescenter.dto.activity.ActivityDto;
import cn.bootx.salescenter.dto.activity.SimpleActivity;
import cn.bootx.salescenter.param.activity.ActivityParam;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.List;

/**   
* 策略注册
* @author xxm  
* @date 2020/10/11 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sc_activity")
public class Activity extends JpaBaseEntity implements EntityBaseFunction<ActivityDto> {

    /** 名称 */
    private String name;

    /** 描述 */
    @Column(name = "`desc`")
    private String desc;

    /** 策略注册id */
    private Long strategyRegisterId;

    /** 策略注册id */
    private String strategyId;

    /** 匹配内容规则*/
    @Transient
    private List<MatchRuleConfig> matchRules;

    /** 检测规则 */
    @Transient
    private List<CheckRuleConfig> checkRules;

    /** 活动互斥 */
    private String activityMutual;

    /** 有效开始时间 */
    private LocalDateTime startTime;

    /** 有效结束时间 */
    private LocalDateTime endTime;

    public static Activity init(ActivityDto dto) {
        return ActivityConvert.INSTANCE.convert(dto);
    }

    public static Activity init(ActivityParam param) {
        return ActivityConvert.INSTANCE.convert(param);
    }

    public SimpleActivity simple(){
        return ActivityConvert.INSTANCE.simple(this);
    }

    @Override
    public ActivityDto toDto() {
        return ActivityConvert.INSTANCE.convert(this);
    }
}
