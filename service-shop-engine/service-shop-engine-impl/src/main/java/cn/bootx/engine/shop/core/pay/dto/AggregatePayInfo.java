package cn.bootx.engine.shop.core.pay.dto;

import cn.bootx.paymentcenter.param.pay.PayModeParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author xxm
* @date 2021/6/24
*/
@Data
@Accessors(chain = true)
@ApiModel("集合支付信息")
public class AggregatePayInfo implements Serializable {
    private static final long serialVersionUID = -6305354592746642055L;

    /** 订单ID */
    private Long orderId;

    /** 支付金额 */
    private BigDecimal amount;

    /** 租户id */
    private Long tid;

    /** 支付信息 */
    private List<PayModeParam> payModeList;
}
