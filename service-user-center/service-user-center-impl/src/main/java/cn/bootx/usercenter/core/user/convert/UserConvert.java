package cn.bootx.usercenter.core.user.convert;

import cn.bootx.usercenter.core.user.entity.UserInfo;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.param.user.UserInfoParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
    UserConvert USER_CONVERT = Mappers.getMapper(UserConvert.class);

    @Mappings({})
    UserInfo convert(UserInfoParam in);

    @Mappings({})
    UserInfo convert(UserInfoDto in);

    @Mappings({})
    UserInfoDto convert(UserInfo in);
}
