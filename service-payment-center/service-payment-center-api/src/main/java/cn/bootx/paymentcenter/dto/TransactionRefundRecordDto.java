package cn.bootx.paymentcenter.dto;

import cn.bootx.common.web.rest.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author xxm
* @date 2020/12/9
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("交易退款记录")
public class TransactionRefundRecordDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 4371362578623528078L;
    private String transactionId;

    private int type;

    private int payType;

    private BigDecimal amount;

    private Long refundOrderId;

    private Long creator;

    private Long paymentId;

    private String account;

}
