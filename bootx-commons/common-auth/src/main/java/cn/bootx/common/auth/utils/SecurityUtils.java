package cn.bootx.common.auth.utils;

import cn.bootx.common.core.code.CommonCode;
import cn.bootx.common.core.entity.UserDetail;
import cn.bootx.common.headerholder.HeaderHolder;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import lombok.experimental.UtilityClass;

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
    public Optional<UserDetail> getUserDetail(){
        return Optional.ofNullable(HeaderHolder.getJwtToken())
                .map(token ->{
                    String user = JWT.decode(token).getClaim(CommonCode.USER).asString();
                    return JSONUtil.toBean(user, UserDetail.class);
                });
    }
}
