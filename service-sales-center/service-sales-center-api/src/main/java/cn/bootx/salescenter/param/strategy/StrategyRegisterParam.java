package cn.bootx.salescenter.param.strategy;

import cn.bootx.salescenter.code.StrategyRegisterCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

import static cn.bootx.common.web.validation.ValidationGroup.add;
import static cn.bootx.common.web.validation.ValidationGroup.edit;

/**
* @author xxm
* @date 2020/12/4
*/
@Data
@Accessors(chain = true)
@ApiModel("策略注册参数类")
public class StrategyRegisterParam implements Serializable {

    private static final long serialVersionUID = -6901032367671927153L;

    @ApiModelProperty("策略主键")
    @NotNull(groups = {add.class, edit.class})
    private Long strategyId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String desc;

    /**
     * @see StrategyRegisterCode
     */
    @ApiModelProperty("策略类型")
    @JsonIgnore
    private Integer strategyType;

    @ApiModelProperty("匹配配置")
    private List<MatchRuleParam> matchRules;

    @ApiModelProperty("选择与使用检查规则")
    private List<CheckRuleParam> checkRules;

    @ApiModelProperty("策略脚本配置项值")
    private List<StrategyConfigValueParam> configValues;
}