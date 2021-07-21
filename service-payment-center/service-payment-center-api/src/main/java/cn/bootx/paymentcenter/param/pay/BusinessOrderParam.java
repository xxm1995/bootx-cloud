package cn.bootx.paymentcenter.param.pay;

import cn.bootx.paymentcenter.param.billing.BillingOrderDetailParam;
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
@ApiModel(value = "商品附加服务额外付款自定义记账")
public class BusinessOrderParam implements Serializable {

    private static final long serialVersionUID = -5200734711022355138L;
    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "订单目的", required = true)
    private int transactionPurpose;

    @ApiModelProperty(value = "订单名目", required = true)
    private int transactionType;

    @ApiModelProperty(value = "是否参与抵扣", required = true)
    private Boolean concessional;

    @ApiModelProperty(value = "实际支付金额", required = true)
    private BigDecimal payAmount;

    @ApiModelProperty(value = "自定义条目列表")
    private List<BillingOrderDetailParam> orderDetailsParamList = new ArrayList<>();

    @ApiModelProperty(value = "未记账金额", hidden = true)
    private BigDecimal leftBillingAmount;

}
