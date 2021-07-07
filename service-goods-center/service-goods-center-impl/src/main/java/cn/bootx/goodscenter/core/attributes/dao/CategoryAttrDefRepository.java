package cn.bootx.goodscenter.core.attributes.dao;

import cn.bootx.goodscenter.core.attributes.entity.CategoryAttrDef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryAttrDefRepository extends JpaRepository<CategoryAttrDef,Long> {
    boolean existsByCidAndNameAndTid(Long cid, String name, Long tid);

    List<CategoryAttrDef> findAllByCidAndTid(Long cid, Long tid);

    List<CategoryAttrDef> findAllByTid(Long tid);

    Optional<CategoryAttrDef> findByIdAndTid(Long id, Long tid);
}
