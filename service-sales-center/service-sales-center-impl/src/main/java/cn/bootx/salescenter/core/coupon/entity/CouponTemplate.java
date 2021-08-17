package cn.bootx.salescenter.core.coupon.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.salescenter.core.check.config.entity.CheckRuleConfig;
import cn.bootx.salescenter.core.coupon.convert.CouponConvert;
import cn.bootx.salescenter.core.match.entity.MatchRuleConfig;
import cn.bootx.salescenter.dto.coupon.CouponTemplateDto;
import cn.bootx.salescenter.param.coupon.CouponTemplateParam;
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
 * 优惠券策略
 * @author xxm
 * @date 2020/10/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sc_coupon_template")
public class CouponTemplate extends JpaBaseEntity implements EntityBaseFunction<CouponTemplateDto> {

    /** 模板名称 */
    private String name;

    /** 策略注册id */
    private Long strategyRegisterId;

    /** 描述 */
    @Column(name = "`desc`")
    private String desc;

    /** 数量 */
    private Integer num;

    /** 匹配内容规则*/
    @Transient
    private List<MatchRuleConfig> matchRules;

    /** 检测规则 */
    @Transient
    private List<CheckRuleConfig> checkRules;

    /** 优惠券互斥 */
    private String couponMutual;

    /** 策略互斥 */
    private String activityMutual;

    /** 有效类型 固定时间段/几天内有效 */
    private Integer effectiveType;

    /** 时间类型 小时/整天/多少天后*/
    private Integer timeType;

    /** 有效时间 */
    private Long effectiveTime;

    /** 有效开始时间 */
    private LocalDateTime startTime;

    /** 有效结束时间 */
    private LocalDateTime endTime;

    public static CouponTemplate init(CouponTemplateDto dto){
        return CouponConvert.INSTANCE.convert(dto);
    }

    public static CouponTemplate init(CouponTemplateParam param){
        return CouponConvert.INSTANCE.convert(param);
    }

    @Override
    public CouponTemplateDto toDto() {
        return CouponConvert.INSTANCE.convert(this);
    }
}
