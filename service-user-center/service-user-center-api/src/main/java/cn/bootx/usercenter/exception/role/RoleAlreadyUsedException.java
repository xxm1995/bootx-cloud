package cn.bootx.usercenter.exception.role;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.usercenter.code.UcErrorCodes.ROLE_ALREADY_USED;

/**
* @author xxm
* @date 2020/4/29 14:42
*/
public class RoleAlreadyUsedException extends BizException implements Serializable {
    private static final long serialVersionUID = 3704932788916299672L;

    public RoleAlreadyUsedException() {
        super(ROLE_ALREADY_USED, "该角色下分配了用户，您无法将其删除.");
    }
}
