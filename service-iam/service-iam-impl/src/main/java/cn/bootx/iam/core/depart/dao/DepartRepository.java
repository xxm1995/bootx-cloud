package cn.bootx.iam.core.depart.dao;

import cn.bootx.iam.core.depart.entity.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
* 部门
* @author xxm
* @date 2020/5/7 17:40
*/
public interface DepartRepository extends JpaRepository<Depart,Long> {

    List<Depart> findAllByTid(Long tid);

    Optional<Depart> findByIdAndTid(Long id, Long tid);

    void deleteByIdAndTid(Long id, Long tid);

    boolean existsByParentIdAndTid(Long parentId, Long tid);
}
