package cn.bootx.bsp.core.channel.convert;

import cn.bootx.bsp.core.channel.entity.Channel;
import cn.bootx.bsp.dto.channel.ChannelDto;
import cn.bootx.bsp.param.channel.ChannelParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* 渠道转换
* @author xxm  
* @date 2021/7/6 
*/
@Mapper
public interface ChannelConvert {
    ChannelConvert CONVERT = Mappers.getMapper(ChannelConvert.class);

    @Mappings({})
    Channel convert(ChannelDto in);
    @Mappings({})
    Channel convert(ChannelParam in);

    @Mappings({})
    ChannelDto convert(Channel in);

}
