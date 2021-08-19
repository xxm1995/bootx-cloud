package cn.bootx.paymentcenter.core.paymodel.cash.entity;

import cn.bootx.paymentcenter.core.paymodel.base.entity.BasePayment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 现金支付张店
* @author xxm
* @date 2021/6/23
*/
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "pc_cash_payment")
@Accessors(chain = true)
public class CashPayment extends BasePayment {
}
