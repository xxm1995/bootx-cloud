package cn.bootx.usercenter.client.feign;

import cn.bootx.usercenter.client.UserInfoClient;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.param.user.UserInfoParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(UserInfoClient.class)
public class UserInfoClientFeignImpl implements UserInfoClient {

    @Autowired(required = false)
    private UserInfoFeign userInfoFeign;

    @Override
    public UserInfoDto getById(Long id){
        return userInfoFeign.getById(id).getData();
    }

    @Override
    public UserInfoDto getByEmail(String email) {
        return userInfoFeign.getByEmail(email).getData();
    }

    @Override
    public UserInfoDto getByPhone(String phone) {
        return userInfoFeign.getByPhone(phone).getData();
    }

    @Override
    public UserInfoDto addUserInfo(UserInfoParam param) {
        return userInfoFeign.addUserInfo(param).getData();
    }
}
