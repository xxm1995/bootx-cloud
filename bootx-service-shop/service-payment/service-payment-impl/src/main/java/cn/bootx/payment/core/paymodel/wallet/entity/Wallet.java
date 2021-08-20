package cn.bootx.payment.core.paymodel.wallet.entity;

import cn.bootx.common.core.function.EntityBaseFunction;
import cn.bootx.common.mybatisplus.base.MpBaseEntity;
import cn.bootx.payment.code.paymodel.WalletCode;
import cn.bootx.payment.core.paymodel.wallet.convert.WalletConvert;
import cn.bootx.payment.dto.paymodel.wallet.WalletDto;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**   
* 钱包
* @author xxm  
* @date 2020/12/8 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("pc_wallet")
public class Wallet extends MpBaseEntity implements EntityBaseFunction<WalletDto> {

    /** 关联用户id */
    private Long userId;

    /** 余额 */
    private BigDecimal balance;

    /** 状态 */
    private Integer status;

    /**
     * 保护方式
     * @see WalletCode
     */
    private Integer protectionMode;

    /** pin码 */
    private String pinCode;

    @Override
    public WalletDto toDto() {
        return WalletConvert.CONVERT.convert(this);
    }
}
