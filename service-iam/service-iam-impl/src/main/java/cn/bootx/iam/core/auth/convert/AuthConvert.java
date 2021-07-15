package cn.bootx.iam.core.auth.convert;

import cn.bootx.iam.core.auth.entity.AuthPassword;
import cn.bootx.iam.core.session.domain.LoginInfoBo;
import cn.bootx.iam.dto.auth.AuthInfoResult;
import cn.bootx.iam.dto.auth.AuthPasswordDto;
import cn.bootx.iam.param.auth.UserAuthParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {
    AuthConvert USER_CONVERT = Mappers.getMapper(AuthConvert.class);

    @Mappings({})
    AuthPassword convert(UserAuthParam in);

    @Mappings({})
    AuthPassword convert(AuthPasswordDto in);

    @Mappings({})
    AuthPasswordDto convert(AuthPassword in);

    @Mappings({})
    AuthInfoResult convert(LoginInfoBo in);
}
