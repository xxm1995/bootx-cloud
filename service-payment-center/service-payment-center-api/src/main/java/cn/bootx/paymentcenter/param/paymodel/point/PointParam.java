package cn.bootx.paymentcenter.param.paymodel.point;

import cn.bootx.paymentcenter.code.paymodel.PointActionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author xxm
* @date 2020/12/11
*/
@Data
@Accessors(chain = true)
@ApiModel("积分参数")
public class PointParam {

    @ApiModelProperty(value = "用户账号id", required = true)
    private Long userId;

    @ApiModelProperty(value = "积分值", required = true)
    private Integer value;

    @ApiModelProperty(value = "操作来源(业务ID)", required = true)
    private String businessId;

    /**
     * @see PointActionEnum
     */
    @ApiModelProperty(value = "来源类型")
    private Integer type;

    @ApiModelProperty(value = "描述")
    private String description;

}
