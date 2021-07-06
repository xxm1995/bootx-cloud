package cn.bootx.bsp.core.tenant.convert;

import cn.bootx.bsp.core.tenant.entity.Tenant;
import cn.bootx.bsp.dto.tenant.TenantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* 租户转换
* @author xxm  
* @date 2021/7/6 
*/
@Mapper
public interface TenantConvert {
    TenantConvert CONVERT = Mappers.getMapper(TenantConvert.class);

    @Mappings({})
    Tenant convert(TenantDto in);

    @Mappings({})
    TenantDto convert(Tenant in);

}
