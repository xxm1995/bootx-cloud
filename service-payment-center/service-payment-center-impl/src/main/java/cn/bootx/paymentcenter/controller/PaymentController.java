package cn.bootx.paymentcenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.paymentcenter.core.payment.service.PaymentQueryService;
import cn.bootx.paymentcenter.dto.pay.PayTypeInfo;
import cn.bootx.paymentcenter.dto.payment.PaymentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @author xxm
* @date 2021/6/28
*/
@Api(tags = "支付记录")
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentQueryService paymentQueryService;

    @ApiOperation("根据id获取支付记录")
    @GetMapping("/findById")
    public ResResult<PaymentDto> findById(Long id){
        return Res.ok(paymentQueryService.findById(id));
    }

    @ApiOperation("根据userId获取支付记录列表")
    @GetMapping("/findByUser")
    public ResResult<List<PaymentDto>> findByUser(Long userid){
        return Res.ok(paymentQueryService.findByUser(userid));
    }

    @ApiOperation("根据businessId获取支付记录列表")
    @GetMapping("/findByBusinessId")
    public ResResult<List<PaymentDto>> findByBusinessId(String businessId){
        return Res.ok(paymentQueryService.findByBusinessId(businessId));
    }

    @ApiOperation("根据businessId获取订单支付方式")
    @GetMapping("/findPayTypeInfoByBusinessId")
    public ResResult<List<PayTypeInfo>> findPayTypeInfoByBusinessId(String businessId){
        return Res.ok(paymentQueryService.findPayTypeInfoByBusinessId(businessId));
    }
    @ApiOperation("根据id获取订单支付方式")
    @GetMapping("/findPayTypeInfoById")
    public ResResult<List<PayTypeInfo>> findPayTypeInfoById(Long id){
        return Res.ok(paymentQueryService.findPayTypeInfoById(id));
    }
}
