package cn.bootx.common.feign.inner;


import cn.bootx.common.core.exception.BizException;

/**   
* 内部服务错误
* @author xxm  
* @date 2021/3/22 
*/
public class InnerServiceException extends BizException {

    public InnerServiceException(int code, String message) {
        super(code, message);
    }
}

