package cn.bootx.starter.headerholder.web;

import cn.bootx.common.web.code.WebHeaderConst;
import cn.bootx.starter.headerholder.HeaderProperties;
import cn.bootx.starter.headerholder.local.HolderContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**   
*
* @author xxm  
* @date 2021/4/20 
*/
@RequiredArgsConstructor
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class WebHeaderHolderInterceptor extends GenericFilterBean {

    private final HeaderProperties headerProperties;

    /** 固定读取 */
    private final List<String> list = Arrays.asList(
            WebHeaderConst.TID,
            WebHeaderConst.OPERATOR_ID,
            WebHeaderConst.ACCESS_TOKEN,
            WebHeaderConst.JWT_TOKEN,
            WebHeaderConst.OP_TOKEN,
            WebHeaderConst.CHANNEL_ID,
            WebHeaderConst.AUTH,
            WebHeaderConst.APP_ID,
            WebHeaderConst.APP_NAME,
            WebHeaderConst.TIMEZONE_NAME
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 固定读取
        for (String header : list) {
            HolderContextHolder.put(header,getHeader(header));
        }

        // 配置读取
        for (String header : headerProperties.getHeaders()) {
            HolderContextHolder.put(header,getHeader(header));
        }

        chain.doFilter(request, response);
        HolderContextHolder.clear();
    }

    /**
     * 获取请求头参数
     */
    private String getHeader(String name){
        // web
        String webHeader = this.getWebHeader(name);
        if (Objects.nonNull(webHeader)){
            return webHeader;
        }
        return null;
    }

    private String getWebHeader(String name) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getHeader(name);
    }


}
