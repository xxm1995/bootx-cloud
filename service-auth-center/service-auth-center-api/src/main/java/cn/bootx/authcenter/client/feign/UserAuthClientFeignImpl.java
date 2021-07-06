package cn.bootx.authcenter.client.feign;

import cn.bootx.authcenter.client.UserAuthClient;
import cn.bootx.authcenter.dto.*;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(UserAuthClient.class)
@RequiredArgsConstructor
public class UserAuthClientFeignImpl implements UserAuthClient {
    @Override
    public UserAuthDto addNew(UserAuthDto dto) {
        return null;
    }

    @Override
    public UserAuthDto saveUserAuthAndInfo(UserAuthDto dto) {
        return null;
    }

    @Override
    public UserInfoDto loginByAllPattern(LoginParam loginParam) {
        return null;
    }

    @Override
    public UserInfoDto loginByAuthCode(AuthCodeLoginParam param) {
        return null;
    }

    @Override
    public UserInfoDto loginByPhone(PhoneLoginParam param) {
        return null;
    }

    @Override
    public UserInfoDto loginByEmail(MailLoginParam param) {
        return null;
    }

    @Override
    public void changePassword(Long userId, String passwordOld, String passwordNew) {

    }

    @Override
    public UserAuthDto activeAccountById(Long id) {
        return null;
    }
}
