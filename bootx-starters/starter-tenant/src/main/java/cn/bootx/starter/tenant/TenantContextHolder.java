package cn.bootx.starter.tenant;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
* 租户上下文工具类
* @author xxm  
* @date 2021/2/28 
*/
public final class TenantContextHolder {
    private static final ThreadLocal<Long> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();

    /**
     * TTL 设置租户ID<br/>
     */
    public static void setTid(Long tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }

    /**
     * 获取TTL中的租户ID
     */
    public static Long getTid() {
        return THREAD_LOCAL_TENANT.get();
    }

    /**
     * 清除
     */
    public static void clear() {
        THREAD_LOCAL_TENANT.remove();
    }
}
