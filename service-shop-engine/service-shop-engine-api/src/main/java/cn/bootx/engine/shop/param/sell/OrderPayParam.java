package cn.bootx.engine.shop.param.sell;

import cn.bootx.paymentcenter.param.pay.PayModeParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxm
* @date 2021/3/9
*/
@Data
@Accessors(chain = true)
@ApiModel("订单支付参数")
public class OrderPayParam implements Serializable {
    private static final long serialVersionUID = 8032630043383571753L;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "支付信息", required = true)
    private List<PayModeParam> payModeList;

}
