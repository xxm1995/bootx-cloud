package cn.bootx.common.feign.exception;

import cn.bootx.common.core.code.CommonCode;
import cn.bootx.common.core.exception.BizException;

/**   
* feign调用时的业务异常
* @author xxm  
* @date 2021/4/27 
*/
public class FeignBizException extends BizException {
    public FeignBizException(int code, String msg) {
        super(code,msg);
    }
    public FeignBizException(String message) {
        super(CommonCode.FAIL_CODE, message);
    }

    public FeignBizException(){
        super();
    }
}
