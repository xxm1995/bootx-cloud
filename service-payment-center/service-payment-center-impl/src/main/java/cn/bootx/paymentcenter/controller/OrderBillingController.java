package cn.bootx.paymentcenter.controller;

import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.paymentcenter.core.billing.service.OrderBillingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xxm
* @date 2021/3/2
*/
@Api(tags = "账单服务")
@RestController
@RequestMapping("/billing")
@RequiredArgsConstructor
public class OrderBillingController {
    private final OrderBillingService billingService;

    @ApiOperation("根据支付id记录进行拆分账单")
    @PostMapping("/handleBilling")
    public ResResult<Void> handleBilling(Long paymentId){
        billingService.handleBilling(paymentId);
        return Res.ok();
    }

}
