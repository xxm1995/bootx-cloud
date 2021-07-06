package cn.bootx.usercenter.client;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.usercenter.dto.role.RoleDto;

import java.util.List;

/**
* 角色
* @author xxm  
* @date 2020/5/26 22:42
*/
public interface RoleClient {

    /**
     * 添加角色
     */
    RoleDto addRole(RoleDto roleDto);

    /**
     * 根据ID删除角色
     */
    void deleteRole(Long id);

    /**
     * 修改角色
     */
    RoleDto updateRole(RoleDto roleDto);

    /**
     * 查询租户下所有的角色
     */
    List<RoleDto> findAll();

    /**
     * 分页查询租户下的角色
     */
    PageResult<RoleDto> page(PageParam pageParam);

    /**
     * 通过ID查询角色
     */
    RoleDto getById(Long id);
}
