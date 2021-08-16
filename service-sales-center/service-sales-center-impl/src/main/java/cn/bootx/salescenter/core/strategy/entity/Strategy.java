package cn.bootx.salescenter.core.strategy.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.core.code.CommonCode;
import cn.bootx.salescenter.code.StrategyCode;
import cn.bootx.salescenter.dto.strategy.StrategyDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 策略定义
* @author xxm
* @date 2020/10/11
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sc_strategy")
@SQLDelete(sql = "update sc_strategy set deleted=" + CommonCode.DELETE_FLAG + " where id=?")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class Strategy extends JpaBaseEntity implements EntityBaseFunction<StrategyDto> {

    /** 策略名称 */
    private String name;

    /** 唯一编码 */
    private String code;

    /** 描述 */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 目标类型
     * @see StrategyCode
     */
    private int targetType;

    /**
     * 应用的引擎类型
     * @see StrategyCode
     */
    private int engineType;

    /** 策略计算脚本 */
    private String ruleScript;

    /** 是否展示 */
    @Column(name = "`show`")
    private Integer show;

    /** 可用状态 */
    private Integer state;

    public static Strategy init(StrategyDto param) {
        Strategy entity = new Strategy();
        BeanUtils.copyProperties(param, entity);
        return entity;
    }

    @Override
    public StrategyDto toDto() {
        StrategyDto dto = new StrategyDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
