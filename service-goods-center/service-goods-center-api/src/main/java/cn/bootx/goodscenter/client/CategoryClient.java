package cn.bootx.goodscenter.client;

import cn.bootx.goodscenter.dto.category.CategoryDto;
import cn.bootx.goodscenter.dto.category.CategoryTreeNode;

import java.util.List;

/**   
* 类目管理
* @author xxm  
* @date 2020/11/26 
*/
public interface CategoryClient {
    /**
     * 添加新类目
     */
    CategoryDto addCategory(CategoryDto categoryDto);

    /**
     * 更新类目基本信息
     */
    CategoryDto update(CategoryDto param);

    /**
     * 获取所有类目
     */
    List<CategoryDto> findAll();

    /**
     * 根据 id 获取相应的类目
     */
    CategoryDto getById(Long id);

    /**
     * 获取类目树
     */
    List<CategoryTreeNode> findTree();

    /**
     * 根据 id 删除相应的类目
     */
    void deleteById(Long id);

}
