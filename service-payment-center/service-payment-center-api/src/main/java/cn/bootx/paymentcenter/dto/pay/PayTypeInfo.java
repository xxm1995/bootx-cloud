package cn.bootx.paymentcenter.dto.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author xxm
* @date 2020/12/8
*/
@Data
@Accessors(chain = true)
@ApiModel("支付类型信息")
public class PayTypeInfo implements Serializable {

    private static final long serialVersionUID = -7757908686367215682L;
    @ApiModelProperty("类型")
    private int type;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("数量")
    private int count;

    @ApiModelProperty(value = "扩展参数的json字符串",hidden = true)
    private String extraParamsJson;

}
