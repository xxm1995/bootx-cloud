package cn.bootx.iam.controller;

import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.iam.core.permission.service.PermissionMenuService;
import cn.bootx.iam.dto.permission.PermissionMenuDto;
import cn.bootx.iam.param.permission.PermissionMenuParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xxm
 * @date 2020/5/11 9:36
 */
@Api(tags = "菜单权限资源")
@RestController
@RequestMapping("/permission/menu")
@RequiredArgsConstructor
public class PermissionMenuController {
    private final PermissionMenuService menuService;

    @ApiOperation("添加菜单权限")
    @PostMapping("/add")
    public ResResult<PermissionMenuDto> add(@RequestBody PermissionMenuParam param){
        return Res.ok(menuService.add(param));
    }


    @ApiOperation("修改菜单权限")
    @PostMapping("/update")
    public ResResult<PermissionMenuDto> update(@RequestBody PermissionMenuParam param){
        return Res.ok(menuService.update(param));
    }

    @ApiOperation("菜单权限列表")
    @GetMapping("/list")
    public ResResult<List<PermissionMenuDto>> list(){
        return Res.ok(menuService.list());
    }
}
