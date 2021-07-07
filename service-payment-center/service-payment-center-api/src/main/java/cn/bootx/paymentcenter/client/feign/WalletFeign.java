package cn.bootx.paymentcenter.client.feign;

import cn.bootx.paymentcenter.code.PaymentCenterCode;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = PaymentCenterCode.APPLICATION_NAME,contextId = "walletFeign",path = "wallet")
public interface WalletFeign {
}
