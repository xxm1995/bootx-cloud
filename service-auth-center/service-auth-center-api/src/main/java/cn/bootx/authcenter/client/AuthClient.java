package cn.bootx.authcenter.client;

import cn.bootx.authcenter.dto.*;

/**
* 认证 登录和退出
* @author xxm  
* @date 2020/5/28 16:16
*/
public interface AuthClient{
    UserAuthResult login(LoginParam param);

    UserAuthResult loginByPhone(PhoneLoginParam param);

    UserAuthResult loginByEmail(MailLoginParam param);

    UserAuthResult loginByAuthCode(AuthCodeLoginParam param);

    /**
     * 通过 token 获取对应的用户信息
     */
    UserAuthResult getUserInfoByToken(String token);

    /**
     * 用户登出
     */
    void logoutByToken(String token);

}
