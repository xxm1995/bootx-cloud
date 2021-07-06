package cn.bootx.noticecenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.noticecenter.code.ErrorCodes.DEFAULT_MAIL_CONFIG_ALREADY_EXISTED;

/**   
* 默认邮箱配置 已存在异常
* @author xxm  
* @date 2020/5/2 14:43 
*/
public class DefaultMailConfigAlreadyExistedException extends BizException implements Serializable {
    private static final long serialVersionUID = 2775127389987392832L;

    public DefaultMailConfigAlreadyExistedException() {
        super(DEFAULT_MAIL_CONFIG_ALREADY_EXISTED, "默认邮箱配置已存在");
    }
}
