package cn.bootx.paymentcenter.core.paymodel.base.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.core.paymodel.base.convert.PayConvert;
import cn.bootx.paymentcenter.dto.pay.PayNotifyRecordDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**   
* 回调记录
* @author xxm  
* @date 2021/6/22 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_pay_notify_record")
public class PayNotifyRecord extends JpaBaseEntity implements EntityBaseFunction<PayNotifyRecordDto> {

    /**
     * 支付号
     */
    private Long paymentId;

    /**
     * 支付类型
     * @see PayTypeCode
     */
    private int type;

    /**
     * 通知消息
     */
    private String notify;

    /**
     * 处理状态
     * @see PayStatusCode
     */
    private int code;

    /** 提示信息 */
    private String msg;

    /**
     * 回调时间
     */
    private LocalDateTime notifyTime;

    @Override
    public PayNotifyRecordDto toDto() {
        return PayConvert.CONVERT.convert(this);
    }
}
