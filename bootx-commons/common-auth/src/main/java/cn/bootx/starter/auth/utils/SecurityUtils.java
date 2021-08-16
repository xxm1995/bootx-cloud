package cn.bootx.starter.auth.utils;

import cn.bootx.common.core.code.WebHeaderConst;
import cn.bootx.common.core.entity.UserDetail;
import cn.bootx.starter.headerholder.local.HolderContextHolder;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

/**   
* 认证工具类
* @author xxm  
* @date 2021/6/3 
*/
@UtilityClass
public class SecurityUtils {
    /**
     * 获取用户
     */
    public UserDetail getUserDetails(){
        return Optional.ofNullable(getHeader(WebHeaderConst.JWT_TOKEN))
                .map(token ->{
                    String user = JWT.decode(token).getClaim("user").asString();
                    return JSONUtil.toBean(user, UserDetail.class);
                }).orElse(null);
    }

    public String getHeader(String name){
        // TTL
        String ttlHeader = HolderContextHolder.get(name);
        if (Objects.nonNull(ttlHeader)){
            return ttlHeader;
        }

        // web
        String webHeader = getWebHeader(name);
        if (Objects.nonNull(webHeader)){
            return webHeader;
        }
        return null;
    }

    private String getWebHeader(String name) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getHeader(name);
    }
}
