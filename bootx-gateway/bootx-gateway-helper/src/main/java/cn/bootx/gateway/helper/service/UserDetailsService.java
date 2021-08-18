package cn.bootx.gateway.helper.service;

import cn.bootx.common.core.entity.UserDetail;
import cn.bootx.gateway.helper.domain.CheckState;
import cn.bootx.gateway.helper.domain.UserDetailsWithResult;
import cn.bootx.iam.client.SessionClient;
import cn.hutool.extra.spring.SpringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/**
* 获取用户信息服务类
* @author xxm
* @date 2021/6/1
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private SessionClient sessionClient;
    private final ExecutorService asyncExecutorService;

    private void init(){
        if (Objects.isNull(sessionClient)){
            sessionClient = SpringUtil.getBean(SessionClient.class);
        }
    }

    public UserDetailsWithResult getUserDetails(String accessToken){
        this.init();
        UserDetailsWithResult result = new UserDetailsWithResult();
        UserDetail userDetail;
        // 异步转同步(filter中无法使用同步阻塞方法)
        try {
            userDetail = asyncExecutorService.submit(() -> sessionClient.getUserDetail()).get();
        } catch (InterruptedException | ExecutionException e) {
            log.warn("token请求失败",e);
            return result.setState(CheckState.PERMISSION_GET_USE_DETAIL_FAILED)
                    .setMessage("认证失败，请稍后重试");
        }

        // 判断用户是否存在
        if (Objects.nonNull(userDetail)){
            result.setUserDetail(userDetail);
        } else {
            result.setState(CheckState.PERMISSION_ACCESS_TOKEN_INVALID)
                    .setMessage("accessToken不合法，请重新登录");
        }
        return result;
    }
}
