package cn.bootx.iam.core.permission.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.common.core.util.ResultConvertUtils;
import cn.bootx.iam.code.permission.PermissionCode;
import cn.bootx.iam.core.permission.dao.PermissionMenuManager;
import cn.bootx.iam.core.permission.dao.PermissionMenuRepository;
import cn.bootx.iam.core.permission.entity.PermissionMenu;
import cn.bootx.iam.dto.permission.PermissionMenuDto;
import cn.bootx.iam.param.permission.PermissionMenuParam;
import cn.bootx.starter.jpa.base.JpaTidEntity;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.bootx.iam.code.CachingCode.*;
import static cn.bootx.iam.code.CachingCode.USER_MENU;

/**
 * 菜单权限
 * @author xxm
 * @date 2021/7/12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionMenuService {
    private final PermissionMenuManager permissionMenuManager;
    private final PermissionMenuRepository permissionMenuRepository;

    /**
     * 添加菜单权限
     */
    @Transactional(rollbackOn = Exception.class)
    public PermissionMenuDto add(PermissionMenuParam param) {
        //----------------------------------------------------------------------
        //判断是否是一级菜单，是的话清空父菜单
        if(PermissionCode.MENU_TYPE_TOP.equals(param.getMenuType())) {
            param.setParentId(null);
        }
        //----------------------------------------------------------------------
        Long pid = param.getParentId();
        if(Objects.nonNull(pid)) {
            //设置父节点不为叶子节点
            permissionMenuManager.setMenuLeaf(pid, false);
        }
        param.setLeaf(true);
        PermissionMenu permissionMenu = PermissionMenu.init(param);
        return permissionMenuRepository.save(permissionMenu).toDto();
    }

    /**
     * 更新
     */
    @Transactional(rollbackOn = Exception.class)
    @CacheEvict(value = {USER_MENU_ID,USER_MENU},allEntries = true)
    public PermissionMenuDto update(PermissionMenuParam param){
        PermissionMenu permissionMenu = permissionMenuManager.findById(param.getId())
                .orElseThrow(() -> new BizException("菜单权限不存在"));
        Long oldPid = permissionMenu.getParentId();
        BeanUtil.copyProperties(param,permissionMenu, CopyOptions.create().ignoreNullValue());

        //1.判断是否是一级菜单，是的话清空父菜单ID
        if(PermissionCode.MENU_TYPE_TOP.equals(permissionMenu.getMenuType())) {
            permissionMenu.setParentId(null);
        }

        //Step2.判断菜单下级是否有菜单，无则设置为叶子节点
        if(!permissionMenuManager.existsByParentId(permissionMenu.getId())) {
            permissionMenu.setLeaf(true);
        }
        PermissionMenuDto permissionMenuDto = permissionMenuRepository.save(permissionMenu).toDto();

        //如果当前菜单的父菜单变了，则需要修改新父菜单和老父菜单的，叶子节点状态
        Long pid = permissionMenu.getParentId();
        if (!Objects.equals(oldPid,pid)){
            //a.设置新的父菜单不为叶子节点
            if (Objects.nonNull(pid)) {
                permissionMenuManager.setMenuLeaf(pid, false);
            }
            //b.判断老的菜单下是否还有其他子菜单，没有的话则设置为叶子节点
            if(Objects.nonNull(oldPid) && !permissionMenuManager.existsByParentId(oldPid)) {
                permissionMenuManager.setMenuLeaf(oldPid, true);
            }

        }
        return permissionMenuDto;
    }

    /**
     * 根据id查询
     */
    public PermissionMenuDto findById(Long id){
        return permissionMenuManager.findById(id)
                .map(PermissionMenu::toDto)
                .orElse(null);
    }

    /**
     * 根据ids查询
     */
    public List<PermissionMenuDto> findByIds(List<Long> permissionIds) {
        return ResultConvertUtils.dtoListConvert(permissionMenuManager.findByIds(permissionIds));
    }

    /**
     * 列表
     */
    public List<PermissionMenuDto> list() {
//        List<Long> collect = permissionMenuManager.findAll().stream()
//                .map(JpaTidEntity::getId)
//                .collect(Collectors.toList());
//        System.out.println(collect);
        return ResultConvertUtils.dtoListConvert(permissionMenuManager.findAll());
    }
}
