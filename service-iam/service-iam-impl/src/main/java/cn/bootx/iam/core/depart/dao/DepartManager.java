package cn.bootx.iam.core.depart.dao;

import cn.bootx.iam.core.depart.entity.Depart;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xxm
 * @date 2020/5/7 17:42
 */
@Repository
@AllArgsConstructor
public class DepartManager {
    private final DepartRepository departRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public List<Depart> findAll() {
        return departRepository.findAllByTid(headerHolder.findTid());
    }

    public Optional<Depart> findById(Long id) {
        return departRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public void deleteById(Long id) {
        departRepository.deleteByIdAndTid(id,headerHolder.findTid());
    }

    public boolean existsParent(Long parentId) {
        return departRepository.existsByParentIdAndTid(parentId,headerHolder.findTid());
    }

    public void deleteByOrgCode(String orgCode) {
        // TODO
    }
}
