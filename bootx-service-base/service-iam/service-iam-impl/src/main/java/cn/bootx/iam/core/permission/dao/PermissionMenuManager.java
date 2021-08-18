package cn.bootx.iam.core.permission.dao;

import cn.bootx.common.mybatisplus.impl.BaseManager;
import cn.bootx.iam.core.permission.entity.PermissionMenu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
* 菜单权限
* @author xxm
* @date 2021/7/12
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class PermissionMenuManager extends BaseManager<PermissionMenuMapper,PermissionMenu> {

    /**
     * 设置是否叶节点状态
     */
    @Transactional(rollbackFor = Exception.class)
    public void setMenuLeaf(Long pid, boolean leaf) {
        lambdaUpdate().eq(PermissionMenu::getParentId,pid)
                .set(PermissionMenu::isLeaf,leaf)
                .update();
    }

    /**
     * 存在子菜单
     */
    public boolean existsByParentId(Long pid){
        return existedByField(PermissionMenu::getParentId,pid);
    }

}
