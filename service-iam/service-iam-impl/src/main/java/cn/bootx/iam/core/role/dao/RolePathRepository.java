package cn.bootx.iam.core.role.dao;

import cn.bootx.iam.core.role.entity.RolePath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**   
* 角色权限
* @author xxm  
* @date 2020/11/14 
*/
public interface RolePathRepository extends JpaRepository<RolePath,Long> {

    List<RolePath> findAllByRoleIdAndTid(Long roleId, Long tid);

    List<RolePath> findAllByRoleIdInAndTid(List<Long> roleIds, Long tid);

    void deleteByRoleIdAndPathIdInAndTid(Long roleId, List<Long> pathIds, Long tid);

    void deleteByRoleIdAndTid(Long roleId, Long tid);

    void deleteByPathIdAndTid(Long pathId, Long tid);

    List<RolePath> findByPathIdAndTid(Long pathId, Long tid);
}
