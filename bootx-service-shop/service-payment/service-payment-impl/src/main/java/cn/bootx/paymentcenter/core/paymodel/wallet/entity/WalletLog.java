package cn.bootx.paymentcenter.core.paymodel.wallet.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.core.code.CommonCode;
import cn.bootx.paymentcenter.dto.paymodel.wallet.WalletLogDto;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**   
* 钱包日志表
* @author xxm  
* @date 2020/12/8 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_wallet_log")
@SQLDelete(sql = "update pc_wallet_log set deleted=" + CommonCode.DELETE_FLAG + " where id=? and version=? ")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class WalletLog extends JpaBaseEntity implements EntityBaseFunction<WalletLogDto> {

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

    public static WalletLog init(WalletLogDto dto){
        WalletLog entity = new WalletLog();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    @Override
    public WalletLogDto toDto(){
        WalletLogDto dto = new WalletLogDto();
        BeanUtils.copyProperties(this,dto);
        return dto;
    }
}
