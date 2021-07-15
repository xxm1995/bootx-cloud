package cn.bootx.iam.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.iam.core.permission.service.PermissionPathService;
import cn.bootx.iam.dto.permission.PermissionPathDto;
import cn.bootx.iam.param.permission.PermissionPathParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
* @author xxm
* @date 2020/5/11 9:36
*/
@Api(tags = "请求权限资源")
@RestController
@RequestMapping("/permission/path")
@RequiredArgsConstructor
public class PermissionPathController {
    private final PermissionPathService pathService;

    @ApiOperation("根据http方式和服务名进行查询")
    @GetMapping("/findByMethodAndService")
    public ResResult<List<PermissionPathDto>> findByMethodAndService(String method, String serviceName){
        return Res.ok(pathService.findByMethodAndService(method,serviceName));
    }

    @ApiOperation("添加权限")
    @PostMapping("/add")
    public ResResult<PermissionPathDto> add(@RequestBody PermissionPathParam param){
        return Res.ok(pathService.add(param));
    }

    @ApiOperation("权限列表")
    @GetMapping("/list")
    public ResResult<List<PermissionPathDto>> list(){
        return Res.ok(pathService.list());
    }

    @ApiOperation("导入")
    @PostMapping("/importPermission")
    public ResResult<Void> importPermission(MultipartFile file){
        pathService.importPermission(file);
        return Res.ok();

    }

}
