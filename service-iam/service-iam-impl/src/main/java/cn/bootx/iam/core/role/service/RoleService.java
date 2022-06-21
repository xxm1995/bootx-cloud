package cn.bootx.iam.core.role.service;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.iam.core.role.dao.RoleManager;
import cn.bootx.iam.core.role.dao.RoleRepository;
import cn.bootx.iam.core.role.dao.UserRoleManager;
import cn.bootx.iam.core.role.entity.Role;
import cn.bootx.iam.dto.role.RoleDto;
import cn.bootx.iam.exception.role.RoleAlreadyExistedException;
import cn.bootx.iam.exception.role.RoleAlreadyUsedException;
import cn.bootx.iam.exception.role.RoleNotExistedException;
import cn.bootx.iam.param.role.RoleParam;
import cn.bootx.starter.jpa.utils.JpaUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author xxm
 * @date 2020/4/28 17:37
 */
@Service
@AllArgsConstructor
public class RoleService {

    private final RoleManager roleManager;
    private final RoleRepository roleRepository;
    private final UserRoleManager userRoleManager;

    /**
     * 添加角色
     */
    @Transactional(rollbackFor = Exception.class)
    public RoleDto add(RoleParam roleParam) {
        //Name唯一性校验（名称code不能相同）
        if (roleManager.existsByCode(roleParam.getName())){
            throw new RoleAlreadyExistedException();
        }
        if (roleManager.existsByName(roleParam.getCode())){
            throw new RoleAlreadyExistedException();
        }
        Role role = Role.init(roleParam);
        return roleRepository.save(role).toDto();
    }

    /**
     * 修改角色
     */
    @Transactional(rollbackFor = Exception.class)
    public RoleDto update(RoleParam roleParam) {
        Long id = roleParam.getId();

        //Name唯一性校验（同一个租户下名称code不能相同）
        if (roleManager.existsByCode(roleParam.getCode(),id)){
            throw new RoleAlreadyExistedException();
        }
        if (roleManager.existsByName(roleParam.getName(),id)){
            throw new RoleAlreadyExistedException();
        }

        Role role = roleManager.findById(id).orElseThrow(RoleNotExistedException::new);
        BeanUtil.copyProperties(roleParam,role, CopyOptions.create().ignoreNullValue());

        return roleRepository.save(role).toDto();
    }

    /**
     * 根据ID删除角色
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        if (Objects.isNull(id) || !roleManager.existsById(id)){
            throw new RoleNotExistedException();
        }
        // 存在当前角色用户的场合不允许删除
        if (userRoleManager.existsByRoleId(id)) {
            throw new RoleAlreadyUsedException();
        }
        // 删除角色信息
        roleRepository.deleteById(id);
    }

    /**
     * 查询租户下所有的角色
     */
    public List<RoleDto> findAll(){
        return roleManager.findAll()
                .stream()
                .map(Role::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 分页查询租户下的角色
     */
    public PageResult<RoleDto> page(PageParam pageParam, RoleParam roleParam){
        Page<Role> page = roleManager.page(pageParam,roleParam);
        return JpaUtils.convert2PageResult(page);
    }

    /**
     * 通过ID查询角色
     */
    public RoleDto findById(Long id) {
        return roleManager.findById(id).map(Role::toDto).orElse(null);
    }
}
