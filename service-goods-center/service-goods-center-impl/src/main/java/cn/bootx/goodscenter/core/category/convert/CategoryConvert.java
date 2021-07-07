package cn.bootx.goodscenter.core.category.convert;

import cn.bootx.goodscenter.core.category.entity.Category;
import cn.bootx.goodscenter.core.attributes.entity.CategoryAttrDef;
import cn.bootx.goodscenter.dto.category.CategoryAttrDefDto;
import cn.bootx.goodscenter.dto.category.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryConvert {
    CategoryConvert CONVERT = Mappers.getMapper(CategoryConvert.class);
    @Mappings({})
    CategoryDto convert(Category in);

    @Mappings({})
    Category convert(CategoryDto in);

    @Mappings({})
    CategoryAttrDefDto convert(CategoryAttrDef in);

    @Mappings({})
    CategoryAttrDef convert(CategoryAttrDefDto in);
}
