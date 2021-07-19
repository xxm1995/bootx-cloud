package cn.bootx.iam.core.role.dao;

import cn.bootx.iam.core.role.entity.RolePath;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    private final HeaderHolder headerHolder;

    public List<RolePath> findByPath(Long pathId){
        return rolePathRepository.findByPathIdAndTid(pathId,headerHolder.findTid());
    }

    public List<RolePath> findByRole(Long roleId) {
        return rolePathRepository.findAllByRoleIdAndTid(roleId,headerHolder.findTid());
    }

    public List<RolePath> findByRoles(List<Long> roleIds) {
        return rolePathRepository.findAllByRoleIdInAndTid(roleIds,headerHolder.findTid());
    }

    public void deleteByRole(Long roleId){
        rolePathRepository.deleteByRoleIdAndTid(roleId,headerHolder.findTid());
    }

    public void deleteByPath(Long pathId){
        rolePathRepository.deleteByPathIdAndTid(pathId,headerHolder.findTid());
    }

}
