package cn.bootx.bsp.core.dictionary.dao;

import cn.bootx.bsp.core.dictionary.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepository extends JpaRepository<Dictionary,Long> {
    /** 根据用户名查询重复 */
    boolean existsByNameAndTid(String name,Long tid);

    /** 根据用户名查询重复 排除id */
    boolean existsByNameAndIdNotAndTid(String name,Long id,Long tid);

    /** 查询子字典的个数 */
    int countByParentIdAndTid(Long parentId,Long tid);

    boolean existsByIdAndTid(Long id, Long tid);

    Optional<Dictionary> findByIdAndTid(Long id, Long tid);

    List<Dictionary> findAllByTid(Long tid);
}
