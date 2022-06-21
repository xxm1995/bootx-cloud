package cn.bootx.iam.controller;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.common.web.util.ValidationUtil;
import cn.bootx.iam.core.role.service.RoleService;
import cn.bootx.iam.dto.role.RoleDto;
import cn.bootx.iam.param.role.RoleParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2021/6/9
*/
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @ApiOperation(value = "添加角色（返回角色对象）")
    @PostMapping(value = "/add")
    public ResResult<RoleDto> add(@RequestBody RoleParam roleParam){
        ValidationUtil.validateParam(roleParam);
        RoleDto result = roleService.add(roleParam);
        return Res.ok(result);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/delete")
    public ResResult<Void> delete(Long id){
        roleService.delete(id);
        return Res.ok();
    }

    @ApiOperation(value = "修改角色（返回角色对象）")
    @PostMapping(value = "/update")
    public ResResult<RoleDto> update(@RequestBody RoleParam roleParam){
        ValidationUtil.validateParam(roleParam);
        RoleDto result = roleService.update(roleParam);
        return Res.ok(result);
    }

    @ApiOperation(value = "通过ID查询角色")
    @GetMapping(value = "/findById")
    public ResResult<RoleDto> findById(Long id){
        return Res.ok(roleService.findById(id));
    }

    @ApiOperation(value = "查询所有的角色")
    @GetMapping(value = "/findAll")
    public ResResult<List<RoleDto>> findAll(){
        return Res.ok(roleService.findAll());
    }

    @ApiOperation(value = "分页查询角色")
    @GetMapping(value = "/page")
    public ResResult<PageResult<RoleDto>> page(PageParam pageParam, RoleParam roleParam){
        return Res.ok(roleService.page(pageParam,roleParam));
    }

}
