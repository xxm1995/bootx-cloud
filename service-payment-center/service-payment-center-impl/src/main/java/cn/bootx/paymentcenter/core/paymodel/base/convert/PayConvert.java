package cn.bootx.paymentcenter.core.paymodel.base.convert;

import cn.bootx.paymentcenter.core.paymodel.base.entity.PayNotifyRecord;
import cn.bootx.paymentcenter.dto.pay.PayNotifyRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**   
* 转换
* @author xxm  
* @date 2021/6/22 
*/
@Mapper
public interface PayConvert {
    PayConvert CONVERT = Mappers.getMapper(PayConvert.class);

    @Mappings({})
    PayNotifyRecord convert(PayNotifyRecordDto in);

    @Mappings({})
    PayNotifyRecordDto convert(PayNotifyRecord in);
}
