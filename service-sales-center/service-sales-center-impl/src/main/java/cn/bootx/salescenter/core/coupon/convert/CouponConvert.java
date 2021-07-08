package cn.bootx.salescenter.core.coupon.convert;

import cn.bootx.salescenter.core.coupon.entity.Coupon;
import cn.bootx.salescenter.core.coupon.entity.CouponTemplate;
import cn.bootx.salescenter.dto.coupon.CouponDto;
import cn.bootx.salescenter.dto.coupon.CouponTemplateDto;
import cn.bootx.salescenter.param.coupon.CouponTemplateParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**   
* 优惠转换
* @author xxm  
* @date 2021/5/20
*/
@Mapper
public interface CouponConvert {
    CouponConvert INSTANCE = Mappers.getMapper(CouponConvert.class);

    @Mappings({
            @Mapping(target = "couponMutual",ignore = true),
            @Mapping(target = "activityMutual",ignore = true)
    })
    CouponTemplate convert(CouponTemplateParam in);

    @Mappings({})
    CouponTemplate convert(CouponTemplateDto in);

    @Mappings({})
    CouponTemplateDto convert(CouponTemplate in);

    @Mappings({})
    Coupon convert(CouponDto in);

    @Mappings({})
    CouponDto convert(Coupon in);
}
