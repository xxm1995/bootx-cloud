package cn.bootx.goodscenter.core.category.dao;

import cn.bootx.goodscenter.core.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**   
* 类目
* @author xxm  
* @date 2020/11/19 
*/
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByNameAndTid(String name, Long tid);

    Optional<Category> findByIdAndTid(Long id, Long tid);

    List<Category> findAllByTid(Long tid);

    void deleteByIdAndTid(Long id, Long tid);
}
