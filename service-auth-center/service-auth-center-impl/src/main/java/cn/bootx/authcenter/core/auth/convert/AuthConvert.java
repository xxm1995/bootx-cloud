package cn.bootx.authcenter.core.auth.convert;

import cn.bootx.authcenter.core.auth.entity.UserAuth;
import cn.bootx.authcenter.core.session.LoginInfoBo;
import cn.bootx.authcenter.dto.UserAuthDto;
import cn.bootx.authcenter.dto.UserAuthResult;
import cn.bootx.authcenter.param.UserAuthParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {
    AuthConvert USER_CONVERT = Mappers.getMapper(AuthConvert.class);

    @Mappings({})
    UserAuth convert(UserAuthParam in);

    @Mappings({})
    UserAuth convert(UserAuthDto in);

    @Mappings({})
    UserAuthDto convert(UserAuth in);

    @Mappings({})
    UserAuthResult convert(LoginInfoBo in);
}
