package cn.bootx.goodscenter.core.category.dao;

import cn.bootx.goodscenter.core.category.entity.Category;
import cn.bootx.common.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* 类目
* @author xxm  
* @date 2020/11/19 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class CategoryManager {
    private final CategoryRepository categoryRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public boolean existsName(String name) {
        return categoryRepository.existsByNameAndTid(name,headerHolder.findTid());
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public List<Category> findAll() {
        return categoryRepository.findAllByTid(headerHolder.findTid());
    }

    public void deleteById(Long id) {
        categoryRepository.deleteByIdAndTid(id,headerHolder.findTid());
    }
}
