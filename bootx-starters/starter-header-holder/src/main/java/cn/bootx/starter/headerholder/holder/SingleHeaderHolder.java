package cn.bootx.starter.headerholder.holder;

import cn.bootx.starter.tenant.TenantProperties;

/**
* 单租户处理
* @author xxm
* @date 2020/11/7
*/
public class SingleHeaderHolder extends DefaultHeaderHolder{
    private final TenantProperties tenantProperties;

    public SingleHeaderHolder(TenantProperties tenantProperties) {
        this.tenantProperties = tenantProperties;
    }

    @Override
    public Long findTid() {
        return tenantProperties.getTenantId();
    }

    @Override
    public Long getTid() {
        return tenantProperties.getTenantId();
    }
}
