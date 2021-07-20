package cn.bootx.gateway.helper.context;

import cn.bootx.common.web.entity.CustomUserDetails;
import cn.bootx.gateway.helper.domain.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
* 请求上下文
* @author xxm  
* @date 2021/5/31 
*/
@Data
@Accessors(chain = true)
public class RequestContext {
    private static final ThreadLocal<RequestContext> CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    /** 请求状态 */
    public final CheckRequest request;

    /** 响应 */
    public final CheckResponse response;

    /** 请求key */
    private RequestKey requestKey;

    /** 权限 */
    private Permission permission;

    /** 路由 */
    private CommonRoute route;

    /** 真实访问地址 */
    private String trueUri;

    /** 用户信息 */
    private CustomUserDetails customUserDetails;

    /** 服务请求对象 */
    private ServerHttpRequest servletRequest;

    public static RequestContext initRequestContext(CheckRequest request, CheckResponse response){
        RequestContext requestContext = new RequestContext(request, response);
        CONTEXT_THREAD_LOCAL.set(requestContext);
        return requestContext;
    }

    public static RequestContext currentRequestContext(){
        return CONTEXT_THREAD_LOCAL.get();
    }

    public static void clearRequestContext(){
        CONTEXT_THREAD_LOCAL.remove();
    }

    public RequestContext(CheckRequest request, CheckResponse builder) {
        this.request = request;
        this.response = builder;
    }

}
