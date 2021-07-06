package cn.bootx.usercenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.usercenter.core.permission.service.PermissionService;
import cn.bootx.usercenter.dto.permission.PermissionDto;
import cn.bootx.usercenter.param.permission.PermissionParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author xxm
* @date 2020/5/11 9:36
*/
@Api(tags = "权限资源")
@RestController
@RequestMapping("/permission")
@AllArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @ApiOperation("根据http方式和服务名进行查询")
    @GetMapping("/findByMethodAndService")
    public ResResult<List<PermissionDto>> findByMethodAndService(String method, String serviceName){
        return Res.ok(permissionService.findByMethodAndService(method,serviceName));
    }

    @ApiOperation("添加权限")
    @PostMapping("/add")
    public ResResult<PermissionDto> add(@RequestBody PermissionParam param){
        return Res.ok(permissionService.add(param));
    }

    @ApiOperation("权限列表")
    @GetMapping("/list")
    public ResResult<List<PermissionDto>> list(){
        return Res.ok(permissionService.list());

    }

    @ApiOperation("导入")
    @PostMapping("/importPermission")
    public ResResult<Void> importPermission(MultipartFile file){
        permissionService.importPermission(file);
        return Res.ok();

    }

}
