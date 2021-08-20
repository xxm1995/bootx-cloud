package cn.bootx.payment.client.feign;

import cn.bootx.payment.code.PaymentCenterCode;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = PaymentCenterCode.APPLICATION_NAME,contextId = "walletFeign",path = "wallet")
public interface WalletFeign {
}
