package cn.bootx.bsp.exception.tenant;


import cn.bootx.bsp.code.BspErrorCodes;
import cn.bootx.common.core.exception.BizException;

/**   
* Tenant 不存在或未激活
* @author xxm  
* @date 2020/4/22 22:57
*/
public class TenantInvalidException extends BizException {
    public TenantInvalidException() {
        super(BspErrorCodes.TENANT_NOT_EXISTED_OR_NOT_ACTIVE, "租户不存在或未激活.");
    }
}
