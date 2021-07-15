package cn.bootx.iam.core.login.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.iam.core.auth.dao.AuthPasswordManager;
import cn.bootx.iam.core.auth.entity.AuthPassword;
import cn.bootx.iam.core.user.service.UserInfoService;
import cn.bootx.iam.dto.user.UserInfoDto;
import cn.bootx.iam.exception.login.UserPasswordInvalidException;
import cn.bootx.iam.exception.user.UserInfoNotExistsException;
import cn.bootx.iam.param.login.LoginPasswordParam;
import cn.bootx.iam.utils.PasswordUtil;
import cn.bootx.iam.utils.RegexUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 账号密码登陆
 * @author xxm
 * @date 2021/7/12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginPasswordService {
    private final UserInfoService userInfoService;
    private final AuthPasswordManager userAuthManager;

    /**
     * 密码登陆
     */
    public UserInfoDto loginPassword(LoginPasswordParam loginParam){
        AuthPassword authPassword;
        UserInfoDto userInfoDto;
        // 1. 获取账号密码
        if (RegexUtil.isEmailPattern(loginParam.getAccount())) {
            // 根据 Email 获取用户信息
            userInfoDto = userInfoService.findByEmail(loginParam.getAccount());
            authPassword = userAuthManager.findByUid(userInfoDto.getId()).orElseThrow(UserInfoNotExistsException::new);
        } else if (RegexUtil.isPhonePattern(loginParam.getAccount())) {
            // 根据 手机号 获取用户信息
            userInfoDto = userInfoService.findByPhone(loginParam.getAccount());
            authPassword = userAuthManager.findByUid(userInfoDto.getId()).orElseThrow(UserInfoNotExistsException::new);
        } else {
            // 根据 账号 获取账户信息
            authPassword = userAuthManager.findByAccount(loginParam.getAccount()).orElseThrow(() -> new BizException("账号或密码错误"));
            userInfoDto = Optional.ofNullable(userInfoService.findById(authPassword.getUid()))
                    .orElseThrow(UserInfoNotExistsException::new);
        }
        // 3. 对比密码认证信息
        String password =  PasswordUtil.md5(loginParam.getPassword());
        String passwordMd5 = PasswordUtil.md5(password, loginParam.getAccount());
        if (!passwordMd5.equals(authPassword.getPassword())) {
            throw new UserPasswordInvalidException();
        }
        return userInfoDto;
    }
}
