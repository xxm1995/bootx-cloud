package cn.bootx.paymentcenter.controller;

import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.paymentcenter.core.pay.service.PayCancelService;
import cn.bootx.paymentcenter.core.pay.service.PaySyncService;
import cn.bootx.paymentcenter.core.paymodel.alipay.service.AliPayCancelService;
import cn.bootx.paymentcenter.core.paymodel.alipay.service.AliPaymentService;
import cn.bootx.paymentcenter.core.paymodel.wechat.service.WeChatPaySyncService;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import cn.bootx.paymentcenter.mq.MessageSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 测试
* @author xxm
* @date 2021/4/19
*/
@Api(tags = "测试控制器")
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {
    private final MessageSender messageSender;
    private final PayCancelService payCancelService;
    private final AliPaymentService aliPaymentService;
    private final PaySyncService paySyncService;
    private final AliPayCancelService aliPayCancelService;
    private final WeChatPaySyncService weChatPaySyncService;

    @ApiOperation("发送消息")
    @PostMapping("/p1")
    public ResResult<Void> p1(){
        PayResult paymentResult = new PayResult()
                .setSyncPayTypeCode(6666)
                .setSyncPayMode(true);

        messageSender.sendPaymentCompleted(paymentResult);
        return Res.ok();
    }


}
