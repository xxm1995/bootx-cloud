package cn.bootx.engine.shop.param.sell;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author xxm
* @date 2021/6/24
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("聚合支付请求参数")
public class OrderAggregatePayParam extends OrderPayParam implements Serializable {
    private static final long serialVersionUID = 8029411112463062621L;

    @ApiModelProperty(value = "支付金额", required = true)
    private BigDecimal amount;

    @ApiModelProperty("授权码(主动扫描用户的付款码),聚合支付用")
    private String authCode;
}
