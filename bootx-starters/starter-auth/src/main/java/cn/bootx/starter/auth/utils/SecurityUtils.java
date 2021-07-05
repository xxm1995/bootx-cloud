package cn.bootx.starter.auth.utils;

import cn.bootx.common.web.entity.CustomUserDetails;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**   
* 认证工具类
* @author xxm  
* @date 2021/6/3 
*/
@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final HeaderHolder headerHolder;

    /**
     * 获取用户
     */
    public CustomUserDetails getUserDetails(){
        return Optional.ofNullable(headerHolder.getJwtToken())
                .map(token ->{
                    String user = JWT.decode(token).getClaim("user").asString();
                    return JSONUtil.toBean(user,CustomUserDetails.class);
                }).orElse(null);
    }
}
