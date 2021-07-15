package cn.bootx.engine.shop.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.engine.shop.mq.MessageSender;
import cn.bootx.goodscenter.param.inventory.UnlockInventoryParam;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.starter.redis.RedisClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Api(tags = "测试控制器")
@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {
    private final MessageSender messageSender;
    private final RedisClient redisClient;
    private final RabbitTemplate rabbitTemplate;

    @ApiOperation("测试stream消息发送")
    @PostMapping("/sendCancelPayment")
    public ResResult<Void> sendCancelPayment(Long id){
        messageSender.sendCancelOrder(123L);
        return Res.ok();
    }

    @ApiOperation("聚合支付测试")
    @GetMapping("/jh")
    public ResResult<String> jh(HttpServletRequest request){
        String ua = request.getHeader("User-Agent");
        if (ua.contains(PayTypeCode.UA_ALI_PAY)) {
            System.out.println("支付宝");
            return Res.ok("支付宝");
        }
        else if (ua.contains(PayTypeCode.UA_WECHAT_PAY)) {
            System.out.println("微信");
            return Res.ok("微信");
        } else {
            System.out.println("未知");
            return Res.ok("未知");
        }
    }

    @ApiOperation("添加数据")
    @PostMapping("/key")
    public ResResult<Void> add(String msg){
        redisClient.setWithTimeout("ce:key", msg,1000*20);
        return Res.ok();
    }

    @ApiOperation("消息测试")
    @GetMapping("/")
    public void pubMsg(String msg){
        UnlockInventoryParam param = new UnlockInventoryParam()
                .setSkuId(1L)
                .setCount(5)
                .setToken("123");
//        messageSender.sendUnlockInventory(param);
        messageSender.sendUnlockCoupon(Arrays.asList(1L,2L));
//        rabbitTemplate.convertAndSend("service.goods.center", "unlock.inventory", param);
//        rabbitTemplate.convertAndSend(SalesEventCode.EXCHANGE_SALES,SalesEventCode.UNLOCK_COUPON,
//                Arrays.asList(1L,2L));
//        rabbitTemplate.convertAndSend(PaymentEventCode.EXCHANGE_PAYMENT,PaymentEventCode.PAY_CANCEL, "param");
    }

}
