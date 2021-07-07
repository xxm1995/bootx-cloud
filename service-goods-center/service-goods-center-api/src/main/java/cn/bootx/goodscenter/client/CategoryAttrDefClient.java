package cn.bootx.goodscenter.client;

import cn.bootx.goodscenter.dto.category.CategoryAttrDefDto;

import java.util.List;

/**   
* 类目属性
* @author xxm  
* @date 2020/11/26 
*/
public interface CategoryAttrDefClient {
    /**
     * 添加类目属性定义
     */
    CategoryAttrDefDto addAttrDef(CategoryAttrDefDto param);

    /**
     * 查询全部定义
     */
    List<CategoryAttrDefDto> findAll();

    /**
     * 根据类目id查询
     */
    List<CategoryAttrDefDto> findByCategory(Long cid);

    /**
     * 查询单条
     */
    CategoryAttrDefDto getById(Long id);
}
