package cn.bootx.payment.core.paymodel.wallet.convert;

import cn.bootx.payment.core.paymodel.wallet.entity.Wallet;
import cn.bootx.payment.core.paymodel.wallet.entity.WalletLog;
import cn.bootx.payment.core.paymodel.wallet.entity.WalletPayment;
import cn.bootx.payment.dto.paymodel.wallet.WalletDto;
import cn.bootx.payment.dto.paymodel.wallet.WalletLogDto;
import cn.bootx.payment.dto.paymodel.wallet.WalletPaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* 转换
* @author xxm
* @date 2021/8/20
*/
@Mapper
public interface WalletConvert {
    WalletConvert CONVERT = Mappers.getMapper(WalletConvert.class);

    @Mappings({})
    WalletDto convert(Wallet in);
    
    @Mappings({})
    WalletPaymentDto convert(WalletPayment in);

    WalletLogDto convert(WalletLog walletLog);
}
