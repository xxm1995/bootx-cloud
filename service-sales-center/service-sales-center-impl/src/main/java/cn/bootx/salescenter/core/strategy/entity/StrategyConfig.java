package cn.bootx.salescenter.core.strategy.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.bootx.salescenter.dto.strategy.StrategyConfigDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 策略配置
* @author xxm  
* @date 2020/10/11 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sc_strategy_config")
public class StrategyConfig extends JpaBaseEntity implements EntityBaseFunction<StrategyConfigDto> {

    /** 策略id */
    private Long strategyId;

    /** 参数名称 */
    @Column(name = "`key`")
    private String key;

    /** 名称 */
    private String name;

    /** 类型 */
    private String type;

    /** 描述 */
    @Column(name = "`desc`")
    private String desc;

    public static StrategyConfig init(StrategyConfigDto param) {
        StrategyConfig entity = new StrategyConfig();
        BeanUtils.copyProperties(param, entity);
        return entity;
    }

    @Override
    public StrategyConfigDto toDto() {
        StrategyConfigDto dto = new StrategyConfigDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
