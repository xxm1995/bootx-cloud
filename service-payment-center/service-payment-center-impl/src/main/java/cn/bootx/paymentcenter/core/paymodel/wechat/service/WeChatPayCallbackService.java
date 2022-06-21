package cn.bootx.paymentcenter.core.paymodel.wechat.service;

import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.code.paymodel.WeChatPayCode;
import cn.bootx.paymentcenter.core.pay.func.AbsPayCallbackStrategy;
import cn.bootx.paymentcenter.core.pay.service.PayCallbackService;
import cn.bootx.paymentcenter.core.paymodel.base.service.PayNotifyRecordService;
import cn.bootx.paymentcenter.core.paymodel.wechat.dao.WeChatPayConfigManager;
import cn.bootx.paymentcenter.core.paymodel.wechat.entity.WeChatPayConfig;
import cn.bootx.starter.redis.RedisClient;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.WxPayKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付回调
 * @author xxm
 * @date 2021/6/21
 */
@Slf4j
@Service
public class WeChatPayCallbackService extends AbsPayCallbackStrategy {
    private final WeChatPayConfigManager weChatPayConfigManager;
    public WeChatPayCallbackService(RedisClient redisClient, PayNotifyRecordService payNotifyRecordService, PayCallbackService payCallbackService, WeChatPayConfigManager weChatPayConfigManager) {
        super(redisClient, payNotifyRecordService, payCallbackService);
        this.weChatPayConfigManager = weChatPayConfigManager;
    }

    @Override
    public int getPayType() {
        return PayTypeCode.WECHAT_PAY;
    }

    /**
     * 获取租户id
     */
    @Override
    public Long getTid() {
        Map<String, String> params = PARAMS.get();
        return Long.valueOf(params.get(WeChatPayCode.ATTACH));
    }

    /**
     * 获取支付单id
     */
    @Override
    public Long getPaymentId() {
        Map<String, String> params = PARAMS.get();
        String paymentId = params.get(WeChatPayCode.OUT_TRADE_NO);
        return Long.valueOf(paymentId);
    }

    /**
     * 获取支付状态
     */
    @Override
    public int getTradeStatus(){
        Map<String, String> params = PARAMS.get();
        if (WxPayKit.codeIsOk(params.get(WeChatPayCode.RESULT_CODE))){
            return PayStatusCode.NOTIFY_TRADE_SUCCESS;
        } else {
            return PayStatusCode.NOTIFY_TRADE_FAIL;
        }
    }

    /**
     * 验证回调消息
     */
    @Override
    public boolean verifyNotify() {
        Map<String, String> params = PARAMS.get();
        String callReq = JSONUtil.toJsonStr(params);
        log.info("微信发起回调 报文: {}", callReq);
        String appId = params.get("appid");

        if (StrUtil.isBlank(appId)) {
            log.warn("微信回调报文 appId 为空 {}", callReq);
            return false;
        }

        WeChatPayConfig weChatPayConfig = weChatPayConfigManager.findByWxAppId(appId).orElse(null);
        if (weChatPayConfig == null) {
            log.warn("微信回调报文 appId 不合法 {}", callReq);
            return false;
        }

        return WxPayKit.verifyNotify(params, weChatPayConfig.getApiKey(), SignType.HMACSHA256);
    }


    @Override
    public String getReturnMsg() {
        Map<String, String> xml = new HashMap<>(4);
        xml.put(WeChatPayCode.RETURN_CODE, "SUCCESS");
        xml.put(WeChatPayCode.RETURN_MSG, "OK");
        return WxPayKit.toXml(xml);
    }
}
