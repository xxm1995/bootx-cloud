package cn.bootx.paymentcenter.core.paymodel.wallet.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.web.code.CommonCode;
import cn.bootx.paymentcenter.core.paymodel.base.entity.BasePayment;
import cn.bootx.paymentcenter.dto.paymodel.wallet.WalletPaymentDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 钱包交易记录表
* @author xxm
* @date 2020/12/8
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "pc_wallet_payment")
@SQLDelete(sql = "update pc_wallet_payment set deleted=" + CommonCode.DELETE_FLAG + " where id=? ")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class WalletPayment extends BasePayment implements EntityBaseFunction<WalletPaymentDto> {

    /** 钱包ID */
    private Long walletId;

    public static WalletPayment init(WalletPaymentDto dto){
        WalletPayment entity = new WalletPayment();
        BeanUtils.copyProperties(dto,entity);
        return entity;
    }

    @Override
    public WalletPaymentDto toDto() {
        WalletPaymentDto dto = new WalletPaymentDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
