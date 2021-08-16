package cn.bootx.bsp.exception.tenant;


import cn.bootx.common.core.exception.BizException;

import static cn.bootx.bsp.code.BspErrorCodes.TENANT_ALREADY_EXISTED;

/**
* 租户已经存在
* @author xxm
* @date 2020/4/22 22:41
*/
public class TenantAlreadyExistedException extends BizException {
    public TenantAlreadyExistedException() {
        super(TENANT_ALREADY_EXISTED, "租户已经存在.");
    }
}
