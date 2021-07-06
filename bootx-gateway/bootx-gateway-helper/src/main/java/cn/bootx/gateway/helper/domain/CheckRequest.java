package cn.bootx.gateway.helper.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpMethod;

/**
* 检查请求
* @author xxm  
* @date 2021/5/31 
*/
@Getter
@ToString
@AllArgsConstructor
public class CheckRequest {
    /** 访问token */
    public final String accessToken;
    /** 请求路径 */
    public final String uri;
    /** 请求方法 */
    public final HttpMethod method;
}
