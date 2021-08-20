package cn.bootx.payment.core.paymodel.wallet.entity;

import cn.bootx.common.core.function.EntityBaseFunction;
import cn.bootx.payment.core.paymodel.base.entity.BasePayment;
import cn.bootx.payment.core.paymodel.wallet.convert.WalletConvert;
import cn.bootx.payment.dto.paymodel.wallet.WalletPaymentDto;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 钱包交易记录表
* @author xxm
* @date 2020/12/8
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("pc_wallet_payment")
public class WalletPayment extends BasePayment implements EntityBaseFunction<WalletPaymentDto> {

    /** 钱包ID */
    private Long walletId;

    @Override
    public WalletPaymentDto toDto() {
        return WalletConvert.CONVERT.convert(this);
    }
}
