package cn.bootx.salescenter.param.strategy;

import cn.bootx.common.web.validation.ValidationGroup.add;
import cn.bootx.common.web.validation.ValidationGroup.edit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* @author xxm
* @date 2021/5/7
*/
@Data
@Accessors(chain = true)
@ApiModel("策略定义配置值")
public class StrategyConfigValueParam implements Serializable {
    private static final long serialVersionUID = -7024845343648657278L;

    @ApiModelProperty(value = "主键")
    @NotNull(groups = edit.class)
    private Long id;

    @ApiModelProperty("策略配置主键")
    @NotNull(groups = {add.class,edit.class})
    private Long strategyConfigId;

    @ApiModelProperty("值")
    @NotNull(groups = {add.class, edit.class})
    private String value;

}