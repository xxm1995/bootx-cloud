package cn.bootx.gateway.helper.api;

import cn.bootx.gateway.helper.context.RequestContext;

/**
* 自定义过滤器，可通过实现该接口加入自定义的鉴权方式
* @author xxm
* @date 2021/5/31
*/
public interface HelperFilter {

    /**
     * filter顺序，越小越先执行
     *
     * @return filter顺序
     */
    int filterOrder();

    /**
     * 是否执行
     *
     * @param context 请求上下文
     * @return true则执行，false不执行
     */
    default boolean shouldFilter(RequestContext context){
        return true;
    }

    /**
     * 执行方法
     *
     * @param context 请求上下文
     * @return true则继续执行后面的filter，false不再执行
     */
    boolean run(RequestContext context);
}
