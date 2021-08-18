package cn.bootx.iam.core.auth.service;

import cn.bootx.common.core.entity.UserDetail;
import cn.bootx.common.headerholder.HeaderHolder;
import cn.bootx.starter.auth.session.domain.LoginSession;
import cn.bootx.starter.auth.session.service.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* 会话管理
* @author xxm
* @date 2021/8/18
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SessionService {
    private final TokenRepository tokenRepository;

    /**
     * 获取会话
     */
    public LoginSession getSession(){
        String accessToken = HeaderHolder.findAccessToken();
        return tokenRepository.getSession(accessToken);
    }

    /**
     * 获取登录用户
     */
    public UserDetail getUserDetail(){
        String accessToken = HeaderHolder.findAccessToken();
        return tokenRepository.getUserDetail(accessToken);
    }
}
