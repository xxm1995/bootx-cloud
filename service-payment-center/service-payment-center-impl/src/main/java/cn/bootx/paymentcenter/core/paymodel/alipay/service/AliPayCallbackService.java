package cn.bootx.paymentcenter.core.paymodel.alipay.service;

import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.code.paymodel.AliPayCode;
import cn.bootx.paymentcenter.core.pay.func.AbsPayCallbackStrategy;
import cn.bootx.paymentcenter.core.pay.service.PayCallbackService;
import cn.bootx.paymentcenter.core.paymodel.alipay.dao.AlipayConfigManager;
import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AlipayConfig;
import cn.bootx.paymentcenter.core.paymodel.base.service.PayNotifyRecordService;
import cn.bootx.starter.redis.RedisClient;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
* 支付宝回调处理
* @author xxm
* @date 2021/2/28
*/
@Slf4j
@Service
public class AliPayCallbackService extends AbsPayCallbackStrategy {
    private final AlipayConfigService alipayConfigService;
    private final AlipayConfigManager alipayConfigManager;

    public AliPayCallbackService(RedisClient redisClient, PayNotifyRecordService payNotifyRecordService, PayCallbackService payCallbackService, AlipayConfigService alipayConfigService, AlipayConfigManager alipayConfigManager) {
        super(redisClient, payNotifyRecordService, payCallbackService);
        this.alipayConfigService = alipayConfigService;
        this.alipayConfigManager = alipayConfigManager;
    }

    @Override
    public int getPayType() {
        return PayTypeCode.ALI_PAY;
    }

    @Override
    public Long getTid() {
        Map<String, String> params = PARAMS.get();
        String tid = params.get(AliPayCode.PASS_BACK_PARAMS);
        if (StrUtil.isBlank(tid)){
            tid = params.get(AliPayCode.BODY);
        }
        return Long.valueOf(tid);
    }

    @Override
    public int getTradeStatus() {
        Map<String, String> params = PARAMS.get();
        String tradeStatus = params.get(AliPayCode.TRADE_STATUS);
        if (Objects.equals(tradeStatus,AliPayCode.NOTIFY_TRADE_SUCCESS)){
            return PayStatusCode.NOTIFY_TRADE_SUCCESS;
        }
        return PayStatusCode.NOTIFY_TRADE_FAIL;
    }

    @Override
    public boolean verifyNotify() {
        Map<String, String> params = PARAMS.get();
        String callReq = JSONUtil.toJsonStr(params);
        String appId = params.get("app_id");
        if (StrUtil.isBlank(appId)) {
            log.warn("支付宝回调报文 appId 为空 {}", callReq);
            return false;
        }
        AlipayConfig alipayConfig = alipayConfigManager.findByAppId(appId).orElse(null);
        if (alipayConfig == null) {
            log.warn("支付宝回调报文 appId 不合法 {}", callReq);
            return false;
        }
        try {
            return AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), CharsetUtil.UTF_8, AlipayConstants.SIGN_TYPE_RSA2);
        }
        catch (AlipayApiException e) {
            log.error("支付宝验签失败", e);
            return false;
        }
    }

    @Override
    public Long getPaymentId() {
        Map<String, String> params = PARAMS.get();
        return Long.valueOf(params.get(AliPayCode.OUT_TRADE_NO));
    }

    @Override
    public String getReturnMsg() {
        return "success";
    }

}
