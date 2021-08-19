package cn.bootx.paymentcenter.param.billing;

import cn.bootx.paymentcenter.code.pay.PayTransactionPurposeCode;
import cn.bootx.paymentcenter.code.pay.PayTransactionTypeCode;
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
* @date 2020/12/10
*/
@Data
@Accessors(chain = true)
@ApiModel("分账订单参数")
public class BillingOrderParam implements Serializable {

    private static final long serialVersionUID = 157222599179861801L;
    @ApiModelProperty(value = "订单ID", required = true)
    private Long id;

    @ApiModelProperty(value = "订单号")
    private String code;

    @ApiModelProperty("订单描述")
    private String description;

    /**
     * @see PayTransactionPurposeCode
     */
    @ApiModelProperty(value = "交易目的", allowableValues = "range[1,7]", required = true)
    private int transactionPurpose;

    /**
     * @see PayTransactionTypeCode
     */
    @ApiModelProperty(value = "交易类型(名目)",allowableValues = "range[1,6]",required = true)
    private int transactionType;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "订单详情列表",required = true)
    private List<BillingOrderDetailParam> orderDetailParams = new ArrayList<>();
}
