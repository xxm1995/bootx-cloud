package cn.bootx.paymentcenter.dto.payment;

import cn.bootx.common.web.rest.dto.BaseDto;
import cn.bootx.paymentcenter.code.pay.PayTransactionPurposeCode;
import cn.bootx.paymentcenter.code.pay.PayTransactionTypeCode;
import cn.bootx.paymentcenter.dto.pay.PayTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @author xxm
* @date 2020/12/9
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("支付交易单")
public class PaymentDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 3269223993950227228L;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty(value = "关联的业务id")
    private String businessId;

    @ApiModelProperty("商户号")
    private String merchantNo;

    @ApiModelProperty("商户应用appId")
    private String appId;

    @ApiModelProperty("是否是异步支付")
    private boolean syncPayMode;

    @ApiModelProperty("异步支付方式")
    private Integer syncPayTypeCode;

    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty("过期时间")
    private LocalDateTime expiredTime;

    @ApiModelProperty("支付状态")
    private int payStatus;

    @ApiModelProperty("金额")
    private BigDecimal amount;
    /**
     * @see PayTransactionPurposeCode
     */
    @ApiModelProperty(value = "交易目的")
    private int transactionPurpose;

    /**
     * @see PayTransactionTypeCode
     */
    @ApiModelProperty(value = "交易名目")
    private int transactionType;

    @ApiModelProperty("错误码")
    private String errorCode;

    @ApiModelProperty("交易类型清单")
    private String transactionTypeList;

    /**
     * @see PayTypeInfo
     */
    @ApiModelProperty("支付类型信息")
    private String payTypeInfo;

}
