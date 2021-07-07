package cn.bootx.goodscenter.core.attributes.service;

import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.goodscenter.core.attributes.dao.CategoryAttrDefManager;
import cn.bootx.goodscenter.core.attributes.dao.CategoryAttrDefRepository;
import cn.bootx.goodscenter.core.attributes.entity.CategoryAttrDef;
import cn.bootx.goodscenter.dto.category.CategoryAttrDefDto;
import cn.bootx.goodscenter.exception.category.CategoryAttrDefAlreadyExistedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**   
* 类目属性定义
* @author xxm  
* @date 2020/11/21 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryAttrDefService {
    private final CategoryAttrDefManager attrDefManager;
    private final CategoryAttrDefRepository attrDefRepository;

    /**
     * 添加类目属性定义
     */
    public CategoryAttrDefDto addAttrDef(CategoryAttrDefDto param){
        if (attrDefManager.existsByCidAndName(param.getCid(), param.getName())) {
            throw new CategoryAttrDefAlreadyExistedException();
        }
        CategoryAttrDef categoryAttrDef = CategoryAttrDef.init(param);
        return attrDefRepository.save(categoryAttrDef).toDto();
    }

    /**
     * 查询全部定义
     */
    public List<CategoryAttrDefDto> findAll(){
        return ResultConvertUtils.dtoListConvert(attrDefManager.findAll());
    }

    /**
     * 根据类目id查询
     */
    public List<CategoryAttrDefDto> findByCategory(Long cid){
        return ResultConvertUtils.dtoListConvert(attrDefManager.findByCid(cid));
    }

    /**
     * 查询单条
     */
    public CategoryAttrDefDto getById(Long id){
        return attrDefManager.findById(id).map(CategoryAttrDef::toDto).orElse(null);
    }

}
