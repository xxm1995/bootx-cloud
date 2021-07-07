package cn.bootx.paymentcenter.param.paymodel.point;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
* @author xxm
* @date 2020/12/11
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "条件查询积分日志参数")
public class PointLogConditionParam {

    @ApiModelProperty(name = "description", value = "积分动作描述", required = true)
    private String description;

    @ApiModelProperty(name = "beginTime", value = "开始时间")
    private LocalDateTime beginTime;

    @ApiModelProperty(name = "endTime", value = "结束时间")
    private LocalDateTime endTime;

}
