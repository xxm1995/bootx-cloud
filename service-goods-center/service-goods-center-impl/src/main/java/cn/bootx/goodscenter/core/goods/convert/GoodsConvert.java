package cn.bootx.goodscenter.core.goods.convert;

import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.goodscenter.dto.goods.GoodsDto;
import cn.bootx.goodscenter.param.goods.GoodsParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**   
* 转换
* @author xxm  
* @date 2021/7/23 
*/
@Mapper
public interface GoodsConvert {
    GoodsConvert CONVERT = Mappers.getMapper(GoodsConvert.class);

    @Mappings({})
    Goods convert(GoodsParam in);

    @Mappings({})
    Goods convert(GoodsDto in);

    @Mappings({})
    GoodsDto convert(Goods in);
}
