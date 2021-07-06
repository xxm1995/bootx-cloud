package cn.bootx.authcenter.client.feign;

import cn.bootx.authcenter.client.AuthClient;
import cn.bootx.authcenter.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(AuthClient.class)
@RequiredArgsConstructor
public class AuthClientFeignImpl implements AuthClient {
    @Autowired(required = false)
    private AuthFeign authFeign;

    @Override
    public UserAuthResult login(LoginParam param){
        return authFeign.login(param).getData();
    }

    @Override
    public UserAuthResult loginByPhone(PhoneLoginParam param){
        return authFeign.loginByPhone(param).getData();
    }

    @Override
    public UserAuthResult loginByEmail(MailLoginParam param){
        return authFeign.loginByEmail(param).getData();
    }

    @Override
    public UserAuthResult loginByAuthCode(AuthCodeLoginParam param){
        return authFeign.loginByAuthCode(param).getData();
    }

    @Override
    public UserAuthResult getUserInfoByToken(String token) {
        return authFeign.getUserInfoByToken(token).getData();
    }

    @Override
    public void logoutByToken(String token){
        authFeign.logoutByToken(token);
    }
}
