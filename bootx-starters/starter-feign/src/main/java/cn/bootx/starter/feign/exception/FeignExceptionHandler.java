package cn.bootx.starter.feign.exception;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.common.web.rest.CommonErrorCodes;
import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import feign.FeignException;
import feign.codec.DecodeException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**   
* feign 异常处理
* @author xxm  
* @date 2021/4/7 
*/
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE-1)
public class FeignExceptionHandler{

    /**
     * 处理 FeignException
     */
    @ExceptionHandler(DecodeException.class)
    public ResResult<Void> handleRuntimeException(DecodeException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof BizException){
            BizException bizException = (BizException) cause;

            if (bizException.getCode() == CommonErrorCodes.UNKNOWN_ERROR){
                return Res.response(CommonErrorCodes.REMOTE_CALL_ERROR,"远程服务器内部错误");
            }
            return Res.response(bizException.getCode(),bizException.getMessage());
        }
        return Res.response(CommonErrorCodes.REMOTE_CALL_ERROR,"远程调用解码失败");
    }

    /**
     * 处理 FeignException
     */
    @ExceptionHandler(FeignException.class)
    public ResResult<Void> handleRuntimeException(FeignException ex) {
        return Res.response(CommonErrorCodes.REMOTE_CALL_ERROR, "远程调用出错");
    }
}
