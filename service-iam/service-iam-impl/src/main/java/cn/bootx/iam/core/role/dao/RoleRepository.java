package cn.bootx.iam.core.role.dao;

import cn.bootx.iam.core.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**   
* 角色
* @author xxm  
* @date 2020/4/28 18:31 
*/
public interface RoleRepository extends JpaRepository<Role,Long> {

    boolean existsByCodeAndTid(String code, Long tid);

    boolean existsByNameAndTid(String name, Long tid);

    boolean existsByIdAndTid(Long id, Long tid);

    boolean existsByCodeAndIdNotAndTid(String code, Long id, Long tid);

    boolean existsByNameAndIdNotAndTid(String name, Long id, Long tid);

    Optional<Role> findByIdAndTid(Long id, Long tid);

    List<Role> findAllByTid(Long tid);

    List<Role> findAllByIdInAndTid(List<Long> ids, Long tid);

}