package cn.bootx.salescenter.core.activity.convert;

import cn.bootx.salescenter.core.activity.entity.Activity;
import cn.bootx.salescenter.dto.activity.ActivityDto;
import cn.bootx.salescenter.dto.activity.SimpleActivity;
import cn.bootx.salescenter.param.activity.ActivityParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* 转换
* @author xxm
* @date 2021/5/19
*/
@Mapper
public interface ActivityConvert {
    ActivityConvert INSTANCE = Mappers.getMapper(ActivityConvert.class);

    @Mappings({})
    Activity convert(ActivityParam in);

    @Mappings({})
    Activity convert(ActivityDto in);

    @Mappings({})
    ActivityDto convert(Activity in);

    @Mappings({})
    SimpleActivity simple(ActivityDto in);

    @Mappings({})
    SimpleActivity simple(Activity in);
}
