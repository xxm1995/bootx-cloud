package cn.bootx.iam.core.role.dao;

import cn.bootx.iam.core.role.entity.RolePath;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * 角色权限
* @author xxm
* @date 2020/5/27 16:02
*/
@Repository
@RequiredArgsConstructor
public class RolePathManager {

    private final RolePathRepository rolePathRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    private final HeaderHolder headerHolder;

    public void deleteByRoleAndPath(Long roleId, List<Long> pathIds) {
        rolePathRepository.deleteByRoleIdAndPathIdInAndTid(roleId,pathIds,headerHolder.findTid());
    }

    public void deleteByRole(Long roleId){
        rolePathRepository.deleteByRoleId(roleId);
    }

    public List<RolePath> findAllByRole(Long roleId) {
        return rolePathRepository.findAllByRoleIdAndTid(roleId,headerHolder.findTid());
    }

    public List<RolePath> findAllByRoles(List<Long> roleIds) {
        return rolePathRepository.findAllByRoleIdInAndTid(roleIds,headerHolder.findTid());
    }

}
