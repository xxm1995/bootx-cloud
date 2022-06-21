package cn.bootx.engine.shop.param.demo;

import cn.bootx.paymentcenter.param.pay.PayModeParam;
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
@ApiModel("支付参数")
public class DemoPayParam implements Serializable {

    private static final long serialVersionUID = -927464570717617012L;

    @ApiModelProperty("支付方式参数")
    private List<PayModeParam> payModeList;
}
