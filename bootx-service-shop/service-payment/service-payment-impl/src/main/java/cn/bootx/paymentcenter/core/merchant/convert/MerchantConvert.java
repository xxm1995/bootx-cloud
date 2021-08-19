package cn.bootx.paymentcenter.core.merchant.convert;

import cn.bootx.paymentcenter.core.merchant.entity.MerchantApp;
import cn.bootx.paymentcenter.core.merchant.entity.MerchantInfo;
import cn.bootx.paymentcenter.dto.merchant.MerchantAppDto;
import cn.bootx.paymentcenter.dto.merchant.MerchantInfoDto;
import cn.bootx.paymentcenter.param.merchant.MerchantAppParam;
import cn.bootx.paymentcenter.param.merchant.MerchantInfoParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**   
* 转换
* @author xxm  
* @date 2021/6/22 
*/
@Mapper
public interface MerchantConvert {
    MerchantConvert CONVERT = Mappers.getMapper(MerchantConvert.class);

    @Mappings({})
    MerchantInfoDto convert(MerchantInfo in);

    @Mappings({})
    MerchantInfo convert(MerchantInfoDto in);

    @Mappings({})
    MerchantInfo convert(MerchantInfoParam in);
    
    @Mappings({})
    MerchantAppDto convert(MerchantApp in);

    @Mappings({})
    MerchantApp convert(MerchantAppDto in);

    @Mappings({})
    MerchantApp convert(MerchantAppParam in);

}
