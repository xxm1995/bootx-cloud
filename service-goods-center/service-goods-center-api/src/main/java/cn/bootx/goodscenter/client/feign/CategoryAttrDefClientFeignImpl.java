package cn.bootx.goodscenter.client.feign;

import cn.bootx.goodscenter.client.CategoryAttrDefClient;
import cn.bootx.goodscenter.dto.category.CategoryAttrDefDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(CategoryAttrDefClient.class)
public class CategoryAttrDefClientFeignImpl implements CategoryAttrDefClient {
    @Autowired(required = false)
    private CategoryAttrDefFeign categoryAttrDefFeign;

    @Override
    public CategoryAttrDefDto addAttrDef(CategoryAttrDefDto param) {
        return categoryAttrDefFeign.add(param).getData();
    }

    @Override
    public List<CategoryAttrDefDto> findAll() {
        return categoryAttrDefFeign.findAll().getData();
    }

    @Override
    public List<CategoryAttrDefDto> findByCategory(Long cid) {
        return categoryAttrDefFeign.findByCategory(cid).getData();
    }

    @Override
    public CategoryAttrDefDto getById(Long id) {
        return categoryAttrDefFeign.get(id).getData();
    }
}
