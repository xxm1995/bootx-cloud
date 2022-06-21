package cn.bootx.paymentcenter.core.billing.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.code.pay.PayTransactionPurposeCode;
import cn.bootx.paymentcenter.code.pay.PayTransactionTypeCode;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.core.billing.convert.BillingConvert;
import cn.bootx.paymentcenter.dto.billing.OrderItemBillingDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
* 订单项账单
* @author xxm
* @date 2020/12/11
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_order_item_billing")
public class OrderItemBilling extends JpaBaseEntity implements EntityBaseFunction<OrderItemBillingDto> {

    /** 账单id */
    private Long orderBillingId;

    /** 用户id */
    private Long userId;

    /** 订单id */
    private Long orderId;

    /** 订单项id */
    private Long orderItemId;

    /** 订单项目skuId */
    private Long skuId;

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

    /**
     * 支付类型
     * @see PayTypeCode
     */
    private Integer payType;

    /** 支付金额 */
    private BigDecimal amount;

    /** 使用数量 */
    private Integer count;

    /** 自定义类型 类型 1.行李箱费 2.自行车费 3.座位费 4.服务费 5.chargeback支付给银行的钱 6.void需要向运营商收取的钱 */
    private Integer businessType;

    /** 入账时间 */
    private Long bookkeepingTime;

    /** 原始订单项id */
    private Long sourceItemBillingId;

    /** 原始订单项d */
    private Long sourceOrderItemId;

    @Override
    public OrderItemBillingDto toDto() {
        return BillingConvert.CONVERT.convert(this);
    }
}
