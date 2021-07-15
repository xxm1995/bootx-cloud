package cn.bootx.iam.core.permission.dao;

import cn.bootx.iam.core.permission.entity.PermissionPath;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 权限
* @author xxm
* @date 2020/5/10 23:26
*/
public interface PermissionPathRepository extends JpaRepository<PermissionPath,Long> {

    List<PermissionPath> findByMethodAndServiceNameAndTid(String method, String serviceName, Long tid);

    List<PermissionPath> findByIdInAndTid(List<Long> ids, Long tid);

    List<PermissionPath> findAllByTid(Long tid);
}
