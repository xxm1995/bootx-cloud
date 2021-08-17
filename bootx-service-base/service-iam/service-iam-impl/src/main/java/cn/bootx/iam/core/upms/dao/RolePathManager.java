package cn.bootx.iam.core.upms.dao;

import cn.bootx.common.mybatisplus.impl.BaseManager;
import cn.bootx.iam.core.upms.entity.RolePath;
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
public class RolePathManager extends BaseManager<RolePathMapper,RolePath> {

    public List<RolePath> findByPath(Long pathId){
        return findAllByField(RolePath::getPathId,pathId);
    }

    public List<RolePath> findByRole(Long roleId) {
        return findAllByField(RolePath::getRoleId,roleId);
    }

    public List<RolePath> findByRoles(List<Long> roleIds) {
        return findAllByFields(RolePath::getRoleId,roleIds);
    }

    public void deleteByRole(Long roleId){
        deleteByField(RolePath::getRoleId,roleId);
    }

    public void deleteByPath(Long pathId){
        deleteByField(RolePath::getPathId,pathId);

    }

}
