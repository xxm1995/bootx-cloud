package cn.bootx.paymentcenter.param.paymodel.point;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
* @author xxm
* @date 2020/12/11
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "分页积分日志参数")
public class PointLogPageParam {

    @ApiModelProperty(name = "userId", value = "用户id", required = true)
    private Long userId;

    @ApiModelProperty(name = "pageNo", value = "页数", required = true)
    @NotNull(message = " pageNo cannot be null")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数", required = true)
    @NotNull(message = "pageSize cannot be null")
    private Integer pageSize;

}
