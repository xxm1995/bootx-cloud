package cn.bootx.starter.feign.inner;

import cn.bootx.common.core.code.CommonCode;
import cn.bootx.common.core.rest.ResResult;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*
* @author xxm
* @date 2021/7/5
*/
public interface InnerHooker {

    Logger LOGGER = LoggerFactory.getLogger(InnerHooker.class);

    /**
     * 404 400 etc.
     */
    default void doFeignException(FeignException fe){
        // do nothing
        throw fe;
    }

    /**
     * Service Producer Exception
     */
    default void doInnerException(ResResult resResult, Class targetClass, String targetMethod, String argsJsonString){
        // 远程调用异常记录
        int code = resResult.getCode();
        if (CommonCode.SUCCESS_CODE != code) {
            String msg = resResult.getMsg();
            LOGGER.info("resResult:{},code:{},msg:{},args:{}", targetClass + ":" + targetMethod,
                    code, msg, argsJsonString);
            throw new InnerServiceException(code, msg);
        }
    }

}
