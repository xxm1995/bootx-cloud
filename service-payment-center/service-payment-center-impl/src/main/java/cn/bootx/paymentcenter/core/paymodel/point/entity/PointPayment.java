package cn.bootx.paymentcenter.core.paymodel.point.entity;

import cn.bootx.paymentcenter.core.paymodel.base.entity.BasePayment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 积分支付记录
 * @author xxm
 * @date 2020/12/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_point_payment")
public class PointPayment extends BasePayment {

    /** 积分生成日志 */
    private Long pointGenerateId;

    /** 积分 */
    private int points;

    /** 业务id */
    private String businessId;

    @Override
    public PointPayment clone() {
        PointPayment clone = new PointPayment();
        clone.setId(this.getId());
        clone.setPaymentId(this.getPaymentId());
        clone.setUserId(this.getUserId());
        clone.setTransactionPurpose(this.getTransactionPurpose());
        clone.setTransactionType(this.getTransactionType());
        clone.setPointGenerateId(this.pointGenerateId);
        clone.setPoints(this.points);
        clone.setCreateTime(this.getCreateTime());
        clone.setPayStatus(this.getPayStatus());
        return clone;
    }
}
