package cn.bootx.iam.core.permission.dao;

import cn.bootx.iam.core.permission.entity.PermissionMenu;
import cn.bootx.iam.core.permission.entity.PermissionPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
* 菜单权限
* @author xxm
* @date 2021/7/12
*/
public interface PermissionMenuRepository extends JpaRepository<PermissionMenu,Long> {

    @Modifying
    @Query("update PermissionMenu set leaf = :leaf where id = :pid and tid = :tid")
    void setMenuLeaf(@Param("pid") Long pid, @Param("leaf") boolean leaf, @Param("tid") Long tid);

    Optional<PermissionMenu> findByIdAndTid(Long id, Long tid);

    boolean existsByParentIdAndTid(Long pid, Long tid);

    List<PermissionMenu> findByIdInAndTid(List<Long> ids, Long tid);

    List<PermissionMenu> findAllByTid(Long tid);
}
