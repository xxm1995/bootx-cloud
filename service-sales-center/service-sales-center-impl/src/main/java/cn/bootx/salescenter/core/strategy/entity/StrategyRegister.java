package cn.bootx.salescenter.core.strategy.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.salescenter.code.StrategyRegisterCode;
import cn.bootx.salescenter.dto.strategy.StrategyRegisterDto;
import cn.bootx.salescenter.param.strategy.StrategyRegisterParam;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 策略注册
* @author xxm  
* @date 2020/10/11 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sc_strategy_register")
public class StrategyRegister extends JpaBaseEntity implements EntityBaseFunction<StrategyRegisterDto> {

    /** 策略id */
    private Long strategyId;

    /** 名称 */
    private String name;

    /** 描述 */
    @Column(name = "`desc`")
    private String desc;

    /**
     * 策略类型(普通/优惠券)
     * @see StrategyRegisterCode
     */
    private Integer strategyType;

    public static StrategyRegister init(StrategyRegisterDto param) {
        StrategyRegister entity = new StrategyRegister();
        BeanUtils.copyProperties(param, entity);
        return entity;
    }

    public static StrategyRegister init(StrategyRegisterParam param) {
        StrategyRegister entity = new StrategyRegister();
        BeanUtils.copyProperties(param, entity);
        return entity;
    }

    @Override
    public StrategyRegisterDto toDto() {
        StrategyRegisterDto dto = new StrategyRegisterDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
