package cn.bootx.gateway.helper.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
* 服务请求工具类
* @author xxm
* @date 2021/6/1
*/
public final class ServerRequestUtils {

    /**
     * 转换请求头
     */
    public static String resolveHeader(ServerHttpRequest request, String key){
        HttpHeaders headers = request.getHeaders();
        List<String> values = headers.get(key);
        if(values == null){
            return null;
        }
        return values.stream()
                .filter(value -> value != null && !value.isEmpty())
                .findFirst()
                .orElse(null);
    }

    /**
     * 转换请求参数
     */
    public static String resolveParam(ServerHttpRequest request, String key){
        MultiValueMap<String, String> params = request.getQueryParams();
        List<String> values = params.get(key);
        if(values == null){
            return null;
        }
        return values.stream()
                .filter(value -> value != null && !value.isEmpty())
                .findFirst()
                .orElse(null);
    }

}
