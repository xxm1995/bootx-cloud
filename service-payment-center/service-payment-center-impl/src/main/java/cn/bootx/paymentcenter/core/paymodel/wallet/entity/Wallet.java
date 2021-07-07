package cn.bootx.paymentcenter.core.paymodel.wallet.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.web.code.CommonCode;
import cn.bootx.paymentcenter.code.paymodel.WalletCode;
import cn.bootx.paymentcenter.dto.paymodel.wallet.WalletDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
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
* 钱包
* @author xxm  
* @date 2020/12/8 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_wallet")
@SQLDelete(sql = "update wallet set deleted=" + CommonCode.DELETE_FLAG + " where id=? and version=? ")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class Wallet extends JpaBaseEntity implements EntityBaseFunction<WalletDto> {

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

    public static Wallet init(WalletDto dto){
        Wallet entity = new Wallet();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    @Override
    public WalletDto toDto() {
        WalletDto dto = new WalletDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
