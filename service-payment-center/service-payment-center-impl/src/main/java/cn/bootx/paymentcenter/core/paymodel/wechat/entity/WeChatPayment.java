package cn.bootx.paymentcenter.core.paymodel.wechat.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.core.paymodel.base.entity.BasePayment;
import cn.bootx.paymentcenter.core.paymodel.wechat.convert.WeChatConvert;
import cn.bootx.paymentcenter.dto.paymodel.wechat.WeChatPaymentDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
*
* @author xxm
* @date 2021/6/21
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_wechat_payment")
public class WeChatPayment extends BasePayment implements EntityBaseFunction<WeChatPaymentDto> {

    /**
     * 微信交易号
     */
    private String tradeNo;

    @Override
    public WeChatPaymentDto toDto() {
        return WeChatConvert.CONVERT.convert(this);
    }
}
