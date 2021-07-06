package cn.bootx.bsp.exception.tenant;


import cn.bootx.common.web.exception.BizException;

import static cn.bootx.bsp.code.BspErrorCodes.TENANT_NOT_EXISTED;

/**
* Tenant 不存在异常
* @author xxm
* @date 2020/4/22 22:42
*/
public class TenantNotExistedException extends BizException {
    public TenantNotExistedException() {
        super(TENANT_NOT_EXISTED, "租户不存在.");
    }
}
