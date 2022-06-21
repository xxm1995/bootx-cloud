package cn.bootx.paymentcenter.param.pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxm
* @date 2020/12/09
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "商品附加服务额外付款自定义支付参数")
public class BusinessPayParam implements Serializable {
    private static final long serialVersionUID = -5333038180632724348L;

    @ApiModelProperty(value = "实际支付金额", required = true)
    private BigDecimal payAmount;

    @ApiModelProperty(value = "参与抵扣的金额")
    private BigDecimal discountAmount;

    @ApiModelProperty(value = "不参与抵扣的金额")
    private BigDecimal noDiscountAmount;

    @ApiModelProperty(value = "商品附加服务额外付款自定义记账参数列表", required = true)
    private List<BusinessOrderParam> orderParamList = new ArrayList<>();

}
