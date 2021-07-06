package cn.bootx.usercenter.core.permission.dao;

import cn.bootx.usercenter.core.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 权限
* @author xxm
* @date 2020/5/10 23:26
*/
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    List<Permission> findByMethodAndServiceNameAndTid(String method, String serviceName, Long tid);

    List<Permission> findByIdInAndTid(List<Long> ids, Long tid);

    List<Permission> findAllByTid(Long tid);
}
