package cn.bootx.payment.core.paymodel.wallet.entity;

import cn.bootx.common.core.function.EntityBaseFunction;
import cn.bootx.common.mybatisplus.base.MpBaseEntity;
import cn.bootx.payment.core.paymodel.wallet.convert.WalletConvert;
import cn.bootx.payment.dto.paymodel.wallet.WalletLogDto;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**   
* 钱包日志表
* @author xxm  
* @date 2020/12/8 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("pc_wallet_log")
public class WalletLog extends MpBaseEntity implements EntityBaseFunction<WalletLogDto> {

    /** 钱包id */
    private Long walletId;

    /** 用户id */
    private Long userId;

    /** 类型 */
    private Integer type;

    /** 交易记录ID */
    private Long paymentId;

    /** 备注 */
    private String remark;

    /** 业务ID */
    private String businessId;

    /** 操作源 */
    private int operationSource;

    /** 金额 */
    private BigDecimal amount;

    @Override
    public WalletLogDto toDto(){
        return WalletConvert.CONVERT.convert(this);
    }
}
