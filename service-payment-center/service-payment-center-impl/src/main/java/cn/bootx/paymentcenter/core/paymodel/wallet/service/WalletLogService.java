package cn.bootx.paymentcenter.core.paymodel.wallet.service;

import cn.bootx.paymentcenter.dto.paymodel.wallet.WalletLogDto;
import cn.bootx.paymentcenter.param.paymodel.wallet.WalletLogQueryParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 钱包日志
* @author xxm
* @date 2020/12/8
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class WalletLogService {
    public List<WalletLogDto> queryLog(WalletLogQueryParam walletLogQueryParam) {
        return null;
    }
}
