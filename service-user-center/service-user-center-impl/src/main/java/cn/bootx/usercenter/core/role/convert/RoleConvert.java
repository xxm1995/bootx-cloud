package cn.bootx.usercenter.core.role.convert;

import cn.bootx.usercenter.core.role.entity.Role;
import cn.bootx.usercenter.dto.role.RoleDto;
import cn.bootx.usercenter.param.role.RoleParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {
    RoleConvert CONVERT = Mappers.getMapper(RoleConvert.class);

    @Mappings({})
    RoleDto convert(Role in);

    @Mappings({})
    Role convert(RoleParam in);

    @Mappings({})
    Role convert(RoleDto in);

}
