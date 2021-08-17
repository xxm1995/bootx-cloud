package cn.bootx.engine.shop.core.pay.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.engine.shop.core.pay.dto.AggregatePayInfo;
import cn.bootx.engine.shop.param.sell.OrderAggregatePayParam;
import cn.bootx.engine.shop.param.sell.OrderPayParam;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.code.paymodel.AliPayCode;
import cn.bootx.paymentcenter.code.paymodel.WeChatPayCode;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import cn.bootx.paymentcenter.param.pay.PayModeParam;
import cn.bootx.paymentcenter.param.paymodel.alipay.AliPayParam;
import cn.bootx.paymentcenter.param.paymodel.wechat.WeChatPayParam;
import cn.bootx.common.headerholder.HeaderHolder;
import cn.bootx.common.redis.RedisClient;
import cn.bootx.common.tenant.TenantContextHolder;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* 聚合支付
* @author xxm
* @date 2021/6/24
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderAggregatePayService {

    private final RedisClient redisClient;
    private final HeaderHolder headerHolder;

    private final OrderPayService orderPayService;

    private final String PREFIX_KEY = "engine:pay:aggregate:";

    /**
     * 创建聚合支付
     */
    public String createAggregatePay(OrderAggregatePayParam param){
        // 保存并生成code
        AggregatePayInfo aggregatePayInfo = new AggregatePayInfo()
                .setOrderId(param.getOrderId())
                .setAmount(param.getAmount())
                .setPayModeList(param.getPayModeList())
                .setTid(headerHolder.findTid());
        String key = RandomUtil.randomString(10);
        redisClient.setWithTimeout(PREFIX_KEY + key, JSONUtil.toJsonStr(aggregatePayInfo),2*60*1000);
        return key;
    }

    /**
     * 扫码发起自动支付
     */
    public String aggregatePay(String key, String ua){
        // 判断是那种支付
        PayModeParam payModeParam = new PayModeParam();
        if (ua.contains(PayTypeCode.UA_ALI_PAY)) {
            AliPayParam aliPayParam = new AliPayParam()
                    .setPayType(AliPayCode.WAP);
            payModeParam.setType(PayTypeCode.ALI_PAY)
                    .setExtraParamsJson(JSONUtil.toJsonStr(aliPayParam));
        }
        else if (ua.contains(PayTypeCode.UA_WECHAT_PAY)) {
            WeChatPayParam weChatPayParam = new WeChatPayParam()
                    .setPayType(WeChatPayCode.WAP);
            payModeParam.setType(PayTypeCode.WECHAT_PAY)
                    .setExtraParamsJson(JSONUtil.toJsonStr(weChatPayParam));
        } else {
            throw new BizException("不支持的支付方式");
        }
        String jsonStr = Optional.ofNullable(redisClient.get(PREFIX_KEY + key))
                .orElseThrow(() -> new BizException("支付超时"));

        AggregatePayInfo aggregatePayInfo = JSONUtil.toBean(jsonStr, AggregatePayInfo.class);
        Long tid = aggregatePayInfo.getTid();
        try {
            TenantContextHolder.setTid(tid);
            payModeParam.setAmount(aggregatePayInfo.getAmount());
            List<PayModeParam> payModeList = Optional.ofNullable(aggregatePayInfo.getPayModeList())
                    .orElse(new ArrayList<>(1));
            payModeList.add(payModeParam);

            OrderPayParam orderPayParam = new OrderPayParam()
                    .setOrderId(aggregatePayInfo.getOrderId())
                    .setPayModeList(payModeList);
            PayResult PayResult = orderPayService.payOrder(orderPayParam);
            return PayResult.getSyncPayInfo().getPayBody();
        } finally {
            TenantContextHolder.clear();
        }
    }

    /**
     * 付款码支付
     */
    public PayResult aggregatePayBarCode(OrderAggregatePayParam param){
        PayModeParam payModeParam = new PayModeParam();
        String authCode = param.getAuthCode();
        if (StrUtil.isBlank(authCode)){
            throw new BizException("付款码不可为空");
        }
        String[] wx = { "10", "11", "12", "13", "14", "15" };
        String[] ali = { "25", "26", "27", "28", "29", "30" };

        if (StrUtil.startWithAny(authCode.substring(0, 2), wx)) {
            AliPayParam aliPayParam = new AliPayParam()
                    .setPayType(AliPayCode.BARCODE)
                    .setAuthCode(authCode);
            payModeParam.setType(PayTypeCode.ALI_PAY)
                    .setExtraParamsJson(JSONUtil.toJsonStr(aliPayParam));
        } else if (StrUtil.startWithAny(authCode.substring(0, 2), ali)) {
            WeChatPayParam weChatPayParam = new WeChatPayParam()
                    .setPayType(WeChatPayCode.BARCODE)
                    .setAuthCode(authCode);
            payModeParam.setType(PayTypeCode.WECHAT_PAY)
                    .setExtraParamsJson(JSONUtil.toJsonStr(weChatPayParam));
        } else {
            throw new BizException("不支持的支付方式");
        }
        payModeParam.setAmount(param.getAmount());
        List<PayModeParam> payModeList = Optional.ofNullable(param.getPayModeList())
                .orElse(new ArrayList<>(1));
        payModeList.add(payModeParam);

        OrderPayParam orderPayParam = new OrderPayParam()
                .setOrderId(param.getOrderId())
                .setPayModeList(payModeList);
        return orderPayService.payOrder(orderPayParam);
    }

}
