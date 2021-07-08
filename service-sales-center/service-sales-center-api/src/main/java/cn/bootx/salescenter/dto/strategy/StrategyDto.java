package cn.bootx.salescenter.dto.strategy;

import cn.bootx.salescenter.code.StrategyCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxm
* @date 2020/10/15
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "策略")
public class StrategyDto implements Serializable {
    private static final long serialVersionUID = 6586989712139176276L;

    @ApiModelProperty("策略主键")
    private Long id;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("描述")
    private String desc;

    /**
     * @see StrategyCode
     */
    @ApiModelProperty("目标类型")
    private int targetType;

    /**
     * @see StrategyCode
     */
    @ApiModelProperty("引擎类型")
    private int engineType;

    @ApiModelProperty("策略计算脚本")
    private String ruleScript;

    @ApiModelProperty("可用状态")
    private Integer state;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("配置项")
    private List<StrategyConfigDto> configParams;

    @ApiModelProperty("是否展示")
    private Integer show;

    @ApiModelProperty(value = "是否可更改")
    private boolean updatable = true;

}
