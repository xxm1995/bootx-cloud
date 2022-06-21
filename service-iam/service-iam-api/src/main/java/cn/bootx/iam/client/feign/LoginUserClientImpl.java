package cn.bootx.iam.client.feign;

import cn.bootx.iam.client.LoginUserClient;
import cn.bootx.iam.dto.auth.AuthInfoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(LoginUserClient.class)
public class LoginUserClientImpl implements LoginUserClient {

    @Autowired(required = false)
    private LoginUserFeign loginUserFeign;

    @Override
    public AuthInfoResult getUserInfo() {
        return loginUserFeign.getUserInfo().getData();
    }
}
