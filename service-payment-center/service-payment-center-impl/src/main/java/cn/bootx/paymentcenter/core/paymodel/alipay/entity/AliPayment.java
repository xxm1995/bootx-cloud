package cn.bootx.paymentcenter.core.paymodel.alipay.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.paymentcenter.core.paymodel.base.entity.BasePayment;
import cn.bootx.paymentcenter.dto.paymodel.alipay.AliPaymentDto;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 支付宝支付记录
* @author xxm  
* @date 2021/2/26 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_ali_payment")
public class AliPayment extends BasePayment implements EntityBaseFunction<AliPaymentDto> {

    /** 支付宝交易号 */
    private String tradeNo;

    @Override
    public AliPaymentDto toDto() {
        AliPaymentDto dto = new AliPaymentDto();
        BeanUtil.copyProperties(this,dto);
        return dto;
    }
}
