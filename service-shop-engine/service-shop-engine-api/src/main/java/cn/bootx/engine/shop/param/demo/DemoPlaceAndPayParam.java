package cn.bootx.engine.shop.param.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxm
* @date 2021/7/23
*/
@Data
@Accessors(chain = true)
@ApiModel("下单与支付参数")
public class DemoPlaceAndPayParam implements Serializable {
    private static final long serialVersionUID = 3936321855327300793L;

    @ApiModelProperty("要下的订单")
    private List<DemoPlaceOrderParam> placeOrder;

    @ApiModelProperty("支付参数")
    private DemoPayParam payParam;

}
