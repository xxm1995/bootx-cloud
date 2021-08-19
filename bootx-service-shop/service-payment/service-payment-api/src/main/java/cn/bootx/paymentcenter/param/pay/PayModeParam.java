package cn.bootx.paymentcenter.param.pay;

import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.dto.pay.PayTypeInfo;
import cn.bootx.paymentcenter.param.paymodel.alipay.AliPayParam;
import cn.bootx.paymentcenter.param.paymodel.wechat.WeChatPayParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
/**
* @author xxm
* @date 2020/12/8
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "支付方式参数")
public class PayModeParam implements Serializable {

    private static final long serialVersionUID = -46959864485463681L;
    /**
     * @see PayTypeCode
     */
    @ApiModelProperty(value = "支付方式", required = true)
    private int type;

    @ApiModelProperty(value = "支付金额", required = true)
    private BigDecimal amount;

    @ApiModelProperty(value = "使用数量", required = true)
    private int count;

    /**
     * @see AliPayParam
     * @see WeChatPayParam
     */
    @ApiModelProperty(value = "扩展参数的json字符串")
    private String extraParamsJson;


    public PayTypeInfo toPayTypeInfo() {
        PayTypeInfo payTypeInfo = new PayTypeInfo();
        BeanUtils.copyProperties(this, payTypeInfo);
        return payTypeInfo;
    }
}
