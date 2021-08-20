package cn.bootx.payment.core.paymodel.alipay.entity;

import cn.bootx.common.core.function.EntityBaseFunction;
import cn.bootx.payment.core.paymodel.base.entity.BasePayment;
import cn.bootx.payment.dto.paymodel.alipay.AliPaymentDto;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**   
* 支付宝支付记录
* @author xxm  
* @date 2021/2/26 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("pc_ali_payment")
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
