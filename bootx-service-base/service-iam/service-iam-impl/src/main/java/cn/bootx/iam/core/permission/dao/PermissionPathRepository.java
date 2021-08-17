package cn.bootx.iam.core.permission.dao;

import cn.bootx.iam.core.permission.entity.PermissionPath;
import cn.bootx.iam.dto.permission.PermissionPathDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 权限
* @author xxm
* @date 2020/5/10 23:26
*/
public interface PermissionPathRepository extends JpaRepository<PermissionPath,Long> {

    List<PermissionPath> findByMethodAndServiceNameAndTid(String method, String serviceName, Long tid);

    List<PermissionPath> findByIdInAndTid(List<Long> ids, Long tid);

    List<PermissionPath> findAllByTid(Long tid);

    Optional<PermissionPath> findByIdAndTid(Long id,Long tid);

    void deleteByIdAndTid(Long id, Long tid);
}
