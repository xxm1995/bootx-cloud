package cn.bootx.iam.core.login.service;

import cn.bootx.iam.core.role.service.UserRoleService;
import cn.bootx.iam.core.session.domain.LoginInfoBo;
import cn.bootx.iam.core.session.service.UserAuthTokenService;
import cn.bootx.iam.dto.auth.AuthInfoResult;
import cn.bootx.iam.dto.user.UserInfoDto;
import cn.bootx.iam.param.login.LoginPasswordParam;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**   
* 用户登录服务
* @author xxm  
* @date 2021/7/12 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserAuthTokenService userAuthTokenService;

    private final LoginPasswordService loginPasswordService;
    private final UserRoleService userRoleService;

    private final HeaderHolder headerHolder;

    /**
     *  手机+验证码登录获取Token
     */
    public AuthInfoResult loginMobile(HttpServletRequest request) {

        return null;
    }

    /**
     * 三方登录获取Token
     */
    public AuthInfoResult loginOpenId(HttpServletRequest request){


        return null;
    }

    /**
     * 密码登陆
     */
    public AuthInfoResult loginPassword(LoginPasswordParam loginParam){
        UserInfoDto userInfoDto = loginPasswordService.loginPassword(loginParam);
        LoginInfoBo loginInfoBo = this.buildLoginInfo(userInfoDto, loginParam.getClient());
        return userAuthTokenService.addAuth(loginInfoBo);
    }

    /**
     * 授权码登陆
     */
    public AuthInfoResult loginAuthCode(){

        return null;
    }

    /**
     * 退出
     */
    public void logout() {
        String accessToken  = headerHolder.findAccessToken();
        if (StrUtil.isBlank(accessToken)){
            return;
        }
        LoginInfoBo vo = userAuthTokenService.getLoginInfo(accessToken);
        if (vo != null && (!Objects.isNull(vo.getUid()))) {
            userAuthTokenService.remove(accessToken);
        }

    }

    /**
     * 构造登录信息
     */
    private LoginInfoBo buildLoginInfo(UserInfoDto userinfo, String client){
        // 获取角色信息
        List<Long> roleIds = userRoleService.findRoleIdsByUser(userinfo.getId());
        return new LoginInfoBo()
                .setClient(client)
                .setAccount(userinfo.getAccount())
                .setUid(userinfo.getId())
                .setName(userinfo.getName())
                .setPhone(userinfo.getPhone())
                .setEmail(userinfo.getEmail())
                .setAdmin(userinfo.isAdmin())
                .setTid(headerHolder.findTid())
                .setRoleIds(roleIds)
                .setLoginTime(LocalDateTime.now());
    }

}
