package cn.bootx.usercenter.core.permission.convert;

import cn.bootx.usercenter.core.permission.entity.Permission;
import cn.bootx.usercenter.dto.permission.PermissionDto;
import cn.bootx.usercenter.param.permission.PermissionParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionConvert {
    PermissionConvert CONVERT = Mappers.getMapper(PermissionConvert.class);

    @Mappings({})
    PermissionDto convert(Permission in);

    @Mappings({})
    Permission convert(PermissionParam in);

    @Mappings({})
    Permission convert(PermissionDto in);

}
