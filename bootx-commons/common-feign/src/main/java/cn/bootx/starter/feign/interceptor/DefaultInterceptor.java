package cn.bootx.starter.feign.interceptor;

import cn.bootx.common.core.code.CommonCode;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.starter.feign.decoder.ResponseInterceptor;
import cn.bootx.starter.feign.exception.FeignBizException;

/**
* 默认feign响应拦截器
* @author xxm
* @date 2021/4/27
*/
public class DefaultInterceptor implements ResponseInterceptor {
    @Override
    public Object apply(Object o) {
        if (o instanceof ResResult){
            ResResult<?> res = (ResResult<?>) o;
            if (res.getCode() != CommonCode.SUCCESS_CODE) {
                throw new FeignBizException(res.getCode(), res.getMsg());
            }
        }
        return o;
    }
}
