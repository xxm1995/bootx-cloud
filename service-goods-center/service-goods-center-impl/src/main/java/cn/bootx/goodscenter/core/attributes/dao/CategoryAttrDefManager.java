package cn.bootx.goodscenter.core.attributes.dao;

import cn.bootx.goodscenter.core.attributes.entity.CategoryAttrDef;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryAttrDefManager {
    private final CategoryAttrDefRepository attrDefRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public boolean existsByCidAndName(Long cid, String name) {
        return attrDefRepository.existsByCidAndNameAndTid(cid,name,headerHolder.findTid());
    }

    public List<CategoryAttrDef> findByCid(Long cid) {
        return attrDefRepository.findAllByCidAndTid(cid,headerHolder.findTid());
    }

    public List<CategoryAttrDef> findAll() {
        return attrDefRepository.findAllByTid(headerHolder.findTid());
    }

    public Optional<CategoryAttrDef> findById(Long id) {
        return attrDefRepository.findByIdAndTid(id,headerHolder.findTid());
    }
}
