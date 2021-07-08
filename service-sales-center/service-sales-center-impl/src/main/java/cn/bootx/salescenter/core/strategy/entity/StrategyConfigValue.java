package cn.bootx.salescenter.core.strategy.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.salescenter.param.strategy.StrategyConfigValueParam;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.bootx.salescenter.dto.strategy.StrategyConfigValueDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

/**   
* 策略配置值
* @author xxm  
* @date 2020/10/11 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sc_strategy_config_value")
public class StrategyConfigValue extends JpaBaseEntity implements EntityBaseFunction<StrategyConfigValueDto> {

    /** 策略id */
    private Long strategyId;

    /** 策略注册id */
    private Long strategyRegisterId;

    /** 策略配置 */
    private Long strategyConfigId;

    @Column(name = "`key`")
    private String key;

    /** 值 */
    private String value;


    public static StrategyConfigValue init(StrategyConfigValueDto param) {
        StrategyConfigValue entity = new StrategyConfigValue();
        BeanUtils.copyProperties(param, entity);
        return entity;
    }

    public static StrategyConfigValue init(StrategyConfigValueParam param) {
        StrategyConfigValue entity = new StrategyConfigValue();
        BeanUtils.copyProperties(param, entity);
        return entity;
    }

    @Override
    public StrategyConfigValueDto toDto() {
        StrategyConfigValueDto dto = new StrategyConfigValueDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
