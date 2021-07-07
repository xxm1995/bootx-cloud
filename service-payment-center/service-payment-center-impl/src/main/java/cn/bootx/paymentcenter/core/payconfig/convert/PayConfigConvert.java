package cn.bootx.paymentcenter.core.payconfig.convert;

import cn.bootx.paymentcenter.core.payconfig.entity.PayChannel;
import cn.bootx.paymentcenter.core.payconfig.entity.PayChannelWay;
import cn.bootx.paymentcenter.dto.payconfig.PayChannelDto;
import cn.bootx.paymentcenter.dto.payconfig.PayChannelWayDto;
import cn.bootx.paymentcenter.param.payconfig.PayChannelParam;
import cn.bootx.paymentcenter.param.payconfig.PayChannelWayParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* 支付配置转换
* @author xxm
* @date 2021/6/30
*/
@Mapper
public interface PayConfigConvert {
    PayConfigConvert CONVERT = Mappers.getMapper(PayConfigConvert.class);

    @Mappings({})
    PayChannelDto convert(PayChannel in);

    @Mappings({})
    PayChannel convert(PayChannelDto in);

    @Mappings({})
    PayChannel convert(PayChannelParam in);
    
    @Mappings({})
    PayChannelWayDto convert(PayChannelWay in);

    @Mappings({})
    PayChannelWay convert(PayChannelWayDto in);

    @Mappings({})
    PayChannelWay convert(PayChannelWayParam in);
}
