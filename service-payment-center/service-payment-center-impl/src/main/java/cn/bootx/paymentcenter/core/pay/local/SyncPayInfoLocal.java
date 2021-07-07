package cn.bootx.paymentcenter.core.pay.local;

import cn.bootx.paymentcenter.dto.pay.AsyncPayInfo;
import com.alibaba.ttl.TransmittableThreadLocal;

/**
* 异步支付线程变量
* @author xxm  
* @date 2021/4/21 
*/
public final class SyncPayInfoLocal {
    private static final ThreadLocal<AsyncPayInfo> THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 设置
     */
    public static void set(AsyncPayInfo syncPayInfo) {
        THREAD_LOCAL.set(syncPayInfo);
    }

    /**
     * 获取
     */
    public static AsyncPayInfo get() {
        return THREAD_LOCAL.get();
    }

    /**
     * 清除
     */
    public static void clear() {
        THREAD_LOCAL.remove();
    }

}
