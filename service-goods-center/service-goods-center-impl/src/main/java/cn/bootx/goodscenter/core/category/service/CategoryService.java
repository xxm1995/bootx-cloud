package cn.bootx.goodscenter.core.category.service;

import cn.bootx.goodscenter.core.category.convert.CategoryConvert;
import cn.bootx.goodscenter.core.category.dao.CategoryManager;
import cn.bootx.goodscenter.core.category.dao.CategoryRepository;
import cn.bootx.goodscenter.core.category.entity.Category;
import cn.bootx.goodscenter.core.category.handler.CategoryTreeHandler;
import cn.bootx.goodscenter.dto.category.CategoryDto;
import cn.bootx.goodscenter.dto.category.CategoryTreeNode;
import cn.bootx.goodscenter.exception.category.CategoryAlreadyExistedException;
import cn.bootx.goodscenter.exception.category.CategoryNotExistedException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* 类目
* @author xxm
* @date 2020/11/19
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryManager categoryManager;


    /**
     * 添加新类目
     */
    @Transactional(rollbackFor = Exception.class)
    public CategoryDto addCategory(CategoryDto categoryDto) {
        if (categoryManager.existsName(categoryDto.getName())) {
            throw new CategoryAlreadyExistedException();
        }
        Category convert = CategoryConvert.CONVERT.convert(categoryDto);
        Category category = categoryRepository.save(convert);
        return category.toDto();
    }

    /**
     * 更新类目基本信息
     */
    @Transactional(rollbackFor = Exception.class)
    public CategoryDto update(CategoryDto param) {
        Category category = categoryManager.findById(param.getId()).orElseThrow(CategoryNotExistedException::new);
        BeanUtil.copyProperties(param,category, CopyOptions.create().ignoreNullValue());
        return categoryRepository.save(category).toDto();
    }

    /**
     * 获取所有类目
     */
    public List<CategoryDto> findAll(){
        return categoryManager.findAll()
                .stream()
                .map(Category::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 根据 id 获取相应的类目
     */
    public CategoryDto getById(Long id){
        return categoryManager.findById(id).map(Category::toDto).orElse(null);
    }

    /**
     * 获取类目树
     */
    public List<CategoryTreeNode> findTree() {
        List<CategoryDto> dtos = this.findAll();
        return CategoryTreeHandler.build(dtos);
    }

    /**
     * 根据 id 删除相应的类目
     */
    public void deleteById(Long id){
        categoryManager.deleteById(id);
    }
}
