package cn.bootx.starter.feign.decoder;

/**   
* 响应拦截器
* @author xxm  
* @date 2021/4/9 
*/
@FunctionalInterface
public interface ResponseInterceptor {

    /**
     * 拦截方法
     */
    Object apply(Object o);
}
