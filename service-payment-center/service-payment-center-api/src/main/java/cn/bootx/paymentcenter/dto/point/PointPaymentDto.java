package cn.bootx.paymentcenter.dto.point;

import cn.bootx.paymentcenter.dto.payment.BasePaymentDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/12/9
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("积分支付日志")
public class PointPaymentDto extends BasePaymentDto implements Serializable {

    private static final long serialVersionUID = -5952544633601726454L;

    @ApiModelProperty(value = "积分生成日志", required = true)
    private Long pointGenerateId;

    @ApiModelProperty(value = "积分值", required = true)
    private Integer points;

    @ApiModelProperty(value = "描述")
    private String desc;

}
