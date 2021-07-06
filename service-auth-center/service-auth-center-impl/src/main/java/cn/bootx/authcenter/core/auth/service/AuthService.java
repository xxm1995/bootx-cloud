package cn.bootx.authcenter.core.auth.service;

import cn.bootx.authcenter.core.auth.dao.UserAuthManager;
import cn.bootx.authcenter.core.auth.entity.AuthCode;
import cn.bootx.authcenter.core.auth.entity.UserAuth;
import cn.bootx.authcenter.core.session.LoginInfoBo;
import cn.bootx.authcenter.core.session.UserAuthTokenService;
import cn.bootx.authcenter.dto.*;
import cn.bootx.authcenter.exception.AccountUnActiveException;
import cn.bootx.authcenter.exception.AuthCodeLoginException;
import cn.bootx.authcenter.exception.UserAccountNotExistedException;
import cn.bootx.authcenter.exception.UserPasswordInvalidException;
import cn.bootx.authcenter.utils.PasswordUtil;
import cn.bootx.authcenter.utils.RegexUtil;
import cn.bootx.usercenter.client.UserInfoClient;
import cn.bootx.usercenter.client.UserRoleClient;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.exception.user.UserInfoNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static cn.bootx.authcenter.code.UserAuthCode.*;

/**
* 认证相关
* @author xxm
* @date 2020/5/28 16:06
*/
@Service
@AllArgsConstructor
public class AuthService {
    private final UserAuthTokenService userAuthTokenService;
    private final UserInfoClient userInfoClient;
    private final UserRoleClient userRoleClient;

    private final UserAuthManager userAuthManager;
    private final AuthCodeService authCodeService;


    /**
     * 账号登录验证
     */
    @Transactional(rollbackFor = Exception.class)
    public UserAuthResult loginByAllPattern(LoginParam loginParam) {

        // 根据账号携带的内容，判断出账号的内容是 手机号、邮箱还是用户名
        UserAuth userAuth;
        if (RegexUtil.isEmailPattern(loginParam.getAccount())) {
            // 根据 Email 获取用户信息
            UserInfoDto userInfoDto = userInfoClient.getByEmail(loginParam.getAccount());
            userAuth = userAuthManager.findByUid(userInfoDto.getId()).orElseThrow(UserAccountNotExistedException::new);
        } else if (RegexUtil.isPhonePattern(loginParam.getAccount())) {
            // 根据 手机号 获取用户信息
            UserInfoDto userInfoDto = userInfoClient.getByPhone(loginParam.getAccount());
            userAuth = userAuthManager.findByUid(userInfoDto.getId()).orElseThrow(UserAccountNotExistedException::new);
        } else {
            // 根据 账号 获取账户信息
            userAuth = userAuthManager.findByAccount(loginParam.getAccount()).orElse(null);
        }

        // 针对 Auth 的各种情况进行验证
        if (Objects.isNull(userAuth)) {
            throw new UserAccountNotExistedException();
        }
        if (!userAuth.isActive()) {
            throw new AccountUnActiveException();
        }

        // 密码对比
        String password =  PasswordUtil.md5(loginParam.getPassword());
        String passwordMd5 = PasswordUtil.md5(password, userAuth.getRegisterTime());
        if (!passwordMd5.equals(userAuth.getPassword())) {
            throw new UserPasswordInvalidException();
        }
        return this.afterLogin(userAuth, loginParam.getClient(), LOGIN_TYPE_ACCOUNT, null);
    }


    /**
     * AuthCode方式登陆
     */
    public UserAuthResult loginByAuthCode(AuthCodeLoginParam param) {

        // 先查询authCode
        AuthCode authCode = authCodeService.getAuthCode(param.getAuthCode(), param.getClient())
                .orElseThrow(AuthCodeLoginException::new);

        authCode = Optional.ofNullable(authCode).orElseThrow(AuthCodeLoginException::new);

        // 根据ID查询
        UserAuth authentication = userAuthManager.findById(authCode.getAuthId())
                .orElseThrow(UserAccountNotExistedException::new);

        if (!authentication.isActive()) {
            throw new AccountUnActiveException();
        }
        return this.afterLogin(authentication, param.getClient(), LOGIN_TYPE_AUTH_CODE, authCode);
    }

    /**
     * 手机验证码登陆
     */
    public UserAuthResult loginByPhone(PhoneLoginParam param) {
        // 根据phone 获取userInfo
        UserInfoDto userInfo = Optional.ofNullable(userInfoClient.getByPhone(param.getPhone()))
                .orElseThrow(UserAccountNotExistedException::new);

        UserAuth authInfo = userAuthManager.findByUid(userInfo.getId())
                .orElseThrow(UserAccountNotExistedException::new);

        if (!authInfo.isActive()) {
            throw new AccountUnActiveException();
        }
        return this.afterLogin(authInfo, param.getClient(), LOGIN_TYPE_PHONE, null);
    }

    /**
     * 邮箱登陆
     */
    public UserAuthResult loginByEmail(MailLoginParam param) {
        // 根据phone 获取userInfo
        UserInfoDto userInfo = userInfoClient.getByEmail(param.getMail());
        if (Objects.isNull(userInfo)) {
            throw new UserAccountNotExistedException();
        }
        UserAuth authInfo = userAuthManager.findByUid(userInfo.getId()).orElseThrow(UserAccountNotExistedException::new);
        if (!authInfo.isActive()) {
            throw new AccountUnActiveException();
        }
        return this.afterLogin(authInfo, param.getClient(), LOGIN_TYPE_PHONE, null);
    }

    /**
     * 通过 token 获取对应的用户信息
     */
    public UserAuthResult getUserInfoByToken(String token) {
        LoginInfoBo loginInfo = userAuthTokenService.getLoginInfo(token);
        // 未登录
        if (loginInfo == null || Objects.isNull(loginInfo.getUid())) {
            return null;
        }
        return loginInfo.toResult().setToken(token);
    }

    /**
     * 登陆后处理 (public是因为需要更新结束时间)
     */
    public UserAuthResult afterLogin(UserAuth userAuth, String client, String loginType, AuthCode authCode) {
        // 非authCode登录需要更新本client终端的authCode
        if (!Objects.equals(loginType, LOGIN_TYPE_AUTH_CODE)) {
            authCode = authCodeService.updateAuthCode(userAuth.getId(), client);
        }

        // 获取用户信息
        UserInfoDto userInfo = Optional.ofNullable(userInfoClient.getById(userAuth.getUid()))
                .orElseThrow(UserInfoNotExistsException::new);
        // 获取角色信息
        List<Long> roleIds = userRoleClient.findRoleIdsByUser(userInfo.getId());

        LocalDateTime lastLoginTime = LocalDateTime.now();
        userAuth.setLastLoginTime(lastLoginTime);
        userAuthManager.update(userAuth);
        String clientAuthCode = authCode == null ? null : authCode.getAuthCode();

        // 添加并生成 session
        return this.addAuthCache(userAuth,userInfo,roleIds,clientAuthCode,client);
    }

    /**
     * 添加认证消息的缓存
     */
    private UserAuthResult addAuthCache(UserAuth userAuth, UserInfoDto userInfo, List<Long> roleIds, String authCode, String client){
        LoginInfoBo loginInfo = new LoginInfoBo()
                .setAccount(userAuth.getAccount())
                .setUid(userAuth.getUid())
                .setTid(userAuth.getTid())
                .setAuthCode(authCode)
                .setRoleIds(roleIds)
                .setClient(client)
                .setName(userInfo.getName())
                .setEmail(userInfo.getEmail())
                .setPhone(userInfo.getPhone())
                .setAdmin(userInfo.isAdmin())
                .setLoginTime(userAuth.getLastLoginTime());

        return userAuthTokenService.addAuth(loginInfo);
    }

    /**
     * 用户登出
     */
    public void logoutByToken(String token) {
        LoginInfoBo vo = userAuthTokenService.getLoginInfo(token);
        if (vo != null && (!Objects.isNull(vo.getUid()))) {
            userAuthTokenService.remove(token);
        }
    }
}
