package cn.bootx.paymentcenter.utils;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**   
* 请求工具类
* @author xxm  
* @date 2021/3/18 
*/
public class RequestUtil {

    private static final Logger LOGGER  = LoggerFactory.getLogger(RequestUtil.class);

    private RequestUtil() {
        // do nothing
    }

    /**
     * 获取请求头信息
     */
    private static final String IP = "source_ip";
    private static final String AGENT = "source_agent";
    private static final String UNKNOW = "unknown";
    private static final String X_FORWARDED_FOR = "x-forwarded-for";
    private static final String PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final String HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    private static final String HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    private static final String SPLIT = ",";

    public static String analyzeIpAddress(HttpServletRequest request) {

        if (StrUtil.isNotBlank(request.getParameter(IP))) {
            return request.getParameter(IP);
        }

        String ip = request.getHeader(X_FORWARDED_FOR);
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_X_FORWARDED_FOR);
        }
        if (ip == null || ip.length() == 0 || UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip.contains(SPLIT)) {
            ip = ip.split(SPLIT)[0];
        }
        return ip;
    }

    private static final String USER_AGENT = "User-Agent";

    public static String analyzeUserAgent(HttpServletRequest request) {

        if (StrUtil.isNotBlank(request.getParameter(AGENT))) {
            return request.getParameter(AGENT);
        }
        return request.getHeader(USER_AGENT);
    }

    private static final String USER_CLIENT= "userClient";

    public static String analyzeUserClient(HttpServletRequest request) {

        if (StrUtil.isNotBlank(request.getParameter(USER_CLIENT))) {
            return request.getParameter(USER_CLIENT);
        }
        return "";
    }
}
