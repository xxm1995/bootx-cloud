package cn.bootx.starter.auth.session.service;


import cn.bootx.common.core.entity.UserDetail;
import cn.bootx.common.jackson.utils.JacksonUtils;
import cn.bootx.common.redis.RedisClient;
import cn.bootx.starter.auth.config.AuthProperties;
import cn.bootx.starter.auth.session.domain.LoginModel;
import cn.bootx.starter.auth.session.domain.LoginSession;
import cn.bootx.starter.auth.session.domain.TokenSign;
import cn.bootx.starter.auth.util.TokenJacksonUtils;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

/**
* token存储
* @author xxm
* @date 2021/8/17
*/
@Component
@RequiredArgsConstructor
public class TokenRepository {

    private static final String KEY_PREFIX_SESSION = "AccessToken:login:session:";
    private static final String KEY_PREFIX_TOKEN = "AccessToken:login:token:";

    private final RedisClient redisClient;
    private final AuthProperties authProperties;


    /**
     * 新增 session，返回 token
     */
    public String login(UserDetail userDetail, String device) {
        return this.login(userDetail,new LoginModel().setDevice(device));
    }


    /**
     * 新增 session，返回 token
     */
    public String login(UserDetail userDetail, LoginModel loginModel) {

        // 判断是否已经已经有会话
        String sessionKey = KEY_PREFIX_SESSION+userDetail.getUserId();
        boolean existsSession = redisClient.exists(sessionKey);

        LoginSession loginSession;
        if (existsSession){
            loginSession = TokenJacksonUtils.toBean(redisClient.get(sessionKey), LoginSession.class);
        } else {
            loginSession = new LoginSession()
                    .setCreateTime(System.currentTimeMillis())
                    .setUserDetail(userDetail);
        }

        String token;
        // 并发登录
        if (authProperties.isConcurrent()){
            token = loginSession.getTokenSignList().stream()
                    .filter(i -> Objects.equals(i.getDevice(), loginModel.getDevice()))
                    .findFirst()
                    .map(TokenSign::getValue)
                    .orElse(RandomUtil.randomString(32));
        } else {
            token = RandomUtil.randomString(32);
            // 将token写入session中
            loginSession.getTokenSignList().add(new TokenSign(token,loginModel.getDevice()));
        }
        // 持久化
        String tokenKey = KEY_PREFIX_TOKEN+RandomUtil.randomString(32);
        redisClient.setWithTimeout(sessionKey,TokenJacksonUtils.toJson(loginSession),authProperties.getTimeout());
        redisClient.setWithTimeout(tokenKey, String.valueOf(userDetail.getUserId()),authProperties.getTimeout());
        return token;
    }

    /**
     * 通过 token 获取登录用户
     */
    public UserDetail getUserDetail(String token) {
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        // 获取
        String userId = redisClient.get(KEY_PREFIX_TOKEN + token);
        if (StrUtil.isBlank(userId)){
            return null;
        }

        return Optional.ofNullable(redisClient.get(KEY_PREFIX_SESSION + userId))
                .map(s -> JacksonUtils.toBean(s,UserDetail.class))
                .orElse(null);
    }

    /**
     * 踢出用户
     */
    public void kickedOutUser(Long uid) {
    }

}
