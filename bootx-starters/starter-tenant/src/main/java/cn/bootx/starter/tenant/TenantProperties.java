package cn.bootx.starter.tenant;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**   
* 租户配置
* @author xxm  
* @date 2020/11/7 
*/
@ConfigurationProperties(prefix = "bootx.starter.tenant")
public class TenantProperties {
    /** 是否单租户 */
    private boolean single = true;
    /** 默认租户值 */
    private long tenantId = 1L;

    public boolean isSingle() {
        return single;
    }

    public TenantProperties setSingle(boolean single) {
        this.single = single;
        return this;
    }

    public long getTenantId() {
        return tenantId;
    }

    public TenantProperties setTenantId(long tenantId) {
        this.tenantId = tenantId;
        return this;
    }
}
