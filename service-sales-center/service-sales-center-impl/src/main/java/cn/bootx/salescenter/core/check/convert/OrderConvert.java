package cn.bootx.salescenter.core.check.convert;

import cn.bootx.salescenter.core.calculate.cache.OrderCache;
import cn.bootx.salescenter.core.calculate.cache.OrderDetailCache;
import cn.bootx.salescenter.param.order.OrderCheckParam;
import cn.bootx.salescenter.param.order.OrderDetailCheckParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**   
* 订单转换
* @author xxm  
* @date 2021/5/19 
*/
@Mapper
public interface OrderConvert {
    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    @Mappings({})
    OrderDetailCheckParam convert(OrderDetailCache in);

    @Mappings({})
    OrderCheckParam convert(OrderCache in);
}
