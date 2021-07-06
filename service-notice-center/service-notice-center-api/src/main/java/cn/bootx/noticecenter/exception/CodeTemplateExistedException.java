package cn.bootx.noticecenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.noticecenter.code.ErrorCodes.MAIL_CONFIG_CODE_ALREADY_EXISTED;

/**   
* 邮箱代码已经存在
* @author xxm  
* @date 2020/6/10 16:21
*/
public class CodeTemplateExistedException extends BizException implements Serializable {
    private static final long serialVersionUID = 6804308428872546951L;

    public CodeTemplateExistedException() {
        super(MAIL_CONFIG_CODE_ALREADY_EXISTED, "邮箱代码已经存在.");
    }
}
