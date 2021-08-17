package cn.bootx.starter.auth.service;

import cn.bootx.starter.auth.authentication.UsernamePasswordAuthentication;
import cn.bootx.starter.auth.entity.AuthInfoResult;
import cn.bootx.starter.auth.exception.LoginFailureException;
import cn.bootx.starter.auth.handler.LoginFailureHandler;
import cn.bootx.starter.auth.handler.LoginSuccessHandler;
import cn.bootx.starter.auth.handler.OpenIdAuthenticationHandler;
import cn.bootx.starter.auth.session.service.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**   
* 认证相关服务
* @author xxm  
* @date 2021/7/30 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {
    private final UsernamePasswordAuthentication usernamePasswordAuthentication;
    private final OpenIdAuthenticationHandler openIdAuthenticationHandler;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;

    private final TokenRepository tokenRepository;

    /**
     * 登录
     */
    public String loginPassword(HttpServletRequest request, HttpServletResponse response){
        AuthInfoResult authInfoResult;
        try {
            authInfoResult = usernamePasswordAuthentication.authentication(request, response);
        } catch (LoginFailureException e) {
            // 登录失败回调
            loginFailureHandler.onLoginFailure(request,response,e);
            throw e;
        }
        String token = tokenRepository.login(authInfoResult.getUserDetail(), authInfoResult.getClient());
        // 登录成功回调
        loginSuccessHandler.onLoginSuccess(request,response,authInfoResult);
        return token;
    }

    /**
     * 三方登录获取Token
     */
    public String loginOpenId(HttpServletRequest request, HttpServletResponse response){
        AuthInfoResult authInfoResult = openIdAuthenticationHandler.authentication(request, response);
        String token = tokenRepository.login(authInfoResult.getUserDetail(), authInfoResult.getClient());
        loginSuccessHandler.onLoginSuccess(request,response,authInfoResult);
        return token;
    }

    /**
     * 退出
     */
    public void logout() {
    }
}
