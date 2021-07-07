package cn.bootx.paymentcenter.core.paymodel.base.entity;

import cn.bootx.paymentcenter.code.pay.PaymentCode;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**   
* 基础支付记录类
* @author xxm  
* @date 2021/2/25 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@MappedSuperclass
public class BasePayment extends JpaBaseEntity {

    /** 交易记录ID */
    private Long paymentId;

    /** 用户ID */
    private Long userId;

    /**
     * 交易目的
     * @see PayTransactionPurposeCode
     */
    private int transactionPurpose;

    /**
     * 交易类型(名目)
     * @see PayTransactionTypeCode
     */
    private int transactionType;

    /** 交易金额 */
    private BigDecimal amount;

    /** 关联的业务id */
    private String businessId;

    /**
     * 支付状态
     * @see PaymentCode
     */
    private Integer payStatus;

    /** 支付时间 */
    private LocalDateTime payTime;


}
