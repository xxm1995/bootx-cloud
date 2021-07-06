package cn.bootx.bsp.core.dictionary.convert;

import cn.bootx.bsp.core.dictionary.entity.Dictionary;
import cn.bootx.bsp.core.dictionary.entity.DictionaryItem;
import cn.bootx.bsp.dto.dictionary.DictionaryDto;
import cn.bootx.bsp.dto.dictionary.DictionaryItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
* 渠道转换
* @author xxm  
* @date 2021/7/6 
*/
@Mapper
public interface DictionaryConvert {
    DictionaryConvert CONVERT = Mappers.getMapper(DictionaryConvert.class);

    @Mappings({})
    Dictionary convert(DictionaryDto in);

    @Mappings({})
    DictionaryDto convert(Dictionary in);

    @Mappings({})
    DictionaryItem convert(DictionaryItemDto in);

    @Mappings({})
    DictionaryItemDto convert(DictionaryItem in);

}
