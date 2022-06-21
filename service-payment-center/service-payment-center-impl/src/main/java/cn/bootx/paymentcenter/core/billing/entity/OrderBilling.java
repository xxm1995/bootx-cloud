package cn.bootx.paymentcenter.core.billing.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.code.pay.PayTransactionPurposeCode;
import cn.bootx.paymentcenter.code.pay.PayTransactionTypeCode;
import cn.bootx.paymentcenter.dto.billing.OrderBillingDto;
import cn.bootx.paymentcenter.utils.PayTypeNameUtil;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**   
* 订单账单
* @author xxm  
* @date 2020/12/11 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_order_billing")
public class OrderBilling extends JpaBaseEntity implements EntityBaseFunction<OrderBillingDto> {

    /** 用户id */
    private Long userId;

    /** 渠道id */
    private Long channelId;

    /** 订单id */
    private Long orderId;

    /**
     * 交易目的
     * @see PayTransactionPurposeCode
     */
    private int transactionPurpose;

    /**
     * 交易类型
     * @see PayTransactionTypeCode
     */
    private int transactionType;

    /** 支付类型 */
    private Integer payType;

    /** 金额 */
    private BigDecimal amount;

    /**
     * 交易数量（积分，代金券，兑换券的场合具体数量
     */
    private Integer count;

    /** 业务id */
    private String businessId;

    @Override
    public OrderBillingDto toDto() {
        OrderBillingDto dto = new OrderBillingDto();
        BeanUtil.copyProperties(this, dto);
        dto.setPayTypeName(
                PayTypeNameUtil.getPayTypeName(dto.getPayType())
        );
        return dto;
    }
}
