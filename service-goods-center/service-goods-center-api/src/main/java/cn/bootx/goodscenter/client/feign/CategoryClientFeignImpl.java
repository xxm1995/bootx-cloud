package cn.bootx.goodscenter.client.feign;

import cn.bootx.goodscenter.client.CategoryClient;
import cn.bootx.goodscenter.dto.category.CategoryDto;
import cn.bootx.goodscenter.dto.category.CategoryTreeNode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(CategoryClient.class)
@RequiredArgsConstructor
public class CategoryClientFeignImpl implements CategoryClient {
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto update(CategoryDto param) {
        return null;
    }

    @Override
    public List<CategoryDto> findAll() {
        return null;
    }

    @Override
    public CategoryDto getById(Long id) {
        return null;
    }

    @Override
    public List<CategoryTreeNode> findTree() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
