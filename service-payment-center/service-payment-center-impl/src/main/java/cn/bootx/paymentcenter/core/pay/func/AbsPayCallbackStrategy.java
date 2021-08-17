package cn.bootx.paymentcenter.core.pay.func;

import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.core.pay.dto.PayCallbackResult;
import cn.bootx.paymentcenter.core.pay.service.PayCallbackService;
import cn.bootx.paymentcenter.core.paymodel.base.entity.PayNotifyRecord;
import cn.bootx.paymentcenter.core.paymodel.base.service.PayNotifyRecordService;
import cn.bootx.common.redis.RedisClient;
import cn.bootx.common.tenant.TenantContextHolder;
import cn.hutool.json.JSONUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Map;

/**
* 支付回调处理抽象接口
* @author xxm
* @date 2021/6/21
*/
@Slf4j
@RequiredArgsConstructor
public abstract class AbsPayCallbackStrategy {
    protected static final ThreadLocal<Map<String,String>> PARAMS = new TransmittableThreadLocal<>();
    private final RedisClient redisClient;
    private final PayNotifyRecordService payNotifyRecordService;
    private final PayCallbackService payCallbackService;

    /**
     * 支付回调
     */
    public String payCallback(Map<String, String> params){
        PARAMS.set(params);
        try {
            log.info("支付回调处理: {}",params);
            TenantContextHolder.setTid(this.getTid());
            // 验证消息
            if (!this.verifyNotify()){
                return null;
            }
            // 去重处理
            if (this.duplicateChecker()){
                return null;
            }
            // 调用统一回调处理
            PayCallbackResult result = payCallbackService.callback(this.getPaymentId(), this.getTradeStatus(), params);
            // 记录回调记录
            this.saveNotifyRecord(result);
        } finally {
            PARAMS.remove();
            TenantContextHolder.clear();
        }
        return this.getReturnMsg();
    }

    /**
     * 支付类型
     * @see PayTypeCode
     */
    public abstract int getPayType();

    /**
     * 去重处理
     */
    public boolean duplicateChecker(){
        // 判断10秒内是否已经回调处理
        String key = "payment:callback:duplicate:" + this.getTid() + ":" + this.getPaymentId();
        return redisClient.setIfAbsent(key, "", 10*1000);
    }

    /**
     * 获取租户
     */
    public abstract Long getTid();

    /**
     * 验证信息格式
     */
    public abstract boolean verifyNotify();

    /**
     * 获取paymentId
     */
    public abstract Long getPaymentId();

    /**
     * 获取支付状态
     * @see PayStatusCode
     */
    public abstract int getTradeStatus();

    /**
     * 返回响应结果
     */
    public abstract String getReturnMsg();

    /**
     * 保存回调记录
     */
    public void saveNotifyRecord(PayCallbackResult result){
        PayNotifyRecord payNotifyRecord = new PayNotifyRecord()
                .setNotify(JSONUtil.toJsonStr(PARAMS.get()))
                .setNotifyTime(LocalDateTime.now())
                .setPaymentId(this.getPaymentId())
                .setType(this.getPayType())
                .setCode(result.getCode())
                .setMsg(result.getMsg());
        payNotifyRecordService.saveNotifyRecord(payNotifyRecord);
    }
}
