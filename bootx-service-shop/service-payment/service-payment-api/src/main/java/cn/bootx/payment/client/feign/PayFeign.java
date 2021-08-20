package cn.bootx.payment.client.feign;

import cn.bootx.common.core.rest.ResResult;
import cn.bootx.payment.code.PaymentCenterCode;
import cn.bootx.payment.dto.payment.PaymentDto;
import cn.bootx.payment.dto.pay.PayResult;
import cn.bootx.payment.param.pay.PayParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**   
* 支付接口
* @author xxm  
* @date 2021/3/21 
*/
@FeignClient(name = PaymentCenterCode.APPLICATION_NAME,contextId = "payFeign",path = "/uni_pay")
public interface PayFeign {

    @ApiOperation("支付")
    @PostMapping("/pay")
    ResResult<PayResult> pay(@RequestBody PayParam payParam);

    @ApiOperation("取消支付(支付id)")
    @PostMapping("/cancelByPaymentId")
    ResResult<Void> cancelByPaymentId(@RequestParam Long paymentId);

    @ApiOperation("取消支付(业务id)")
    @PostMapping("/cancelByBusinessId")
    ResResult<Void> cancelByBusinessId(@RequestParam String businessId);

    @ApiOperation("刷新指定业务id的支付单状态")
    @PostMapping("/syncByBusinessId")
    ResResult<PaymentDto> syncByBusinessId(@RequestParam String businessId);
}
