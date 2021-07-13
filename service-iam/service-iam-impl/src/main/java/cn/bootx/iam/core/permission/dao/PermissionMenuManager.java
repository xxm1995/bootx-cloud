package cn.bootx.iam.core.permission.dao;

import cn.bootx.iam.core.permission.entity.PermissionMenu;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
* 菜单权限
* @author xxm
* @date 2021/7/12
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class PermissionMenuManager {
    private final PermissionMenuRepository repository;
    private final HeaderHolder headerHolder;

    /**
     * 设置是否叶节点状态
     */
    @Transactional(rollbackOn = Exception.class)
    public void setMenuLeaf(Long pid, boolean leaf) {
        repository.setMenuLeaf(pid,leaf,headerHolder.findTid());
    }

    /**
     * 存在子菜单
     */
    public boolean existsByParentId(Long pid){
        return repository.existsByParentIdAndTid(pid,headerHolder.findTid());
    }

    /**
     * 根据id查询
     */
    public Optional<PermissionMenu> findById(Long id){
        return repository.findByIdAndTid(id,headerHolder.findTid());
    }

    public List<PermissionMenu> findByIds(List<Long> ids) {
        return repository.findByIdInAndTid(ids,headerHolder.findTid());
    }

    public List<PermissionMenu> findAll() {
        return repository.findAllByTid(headerHolder.findTid());
    }
}
