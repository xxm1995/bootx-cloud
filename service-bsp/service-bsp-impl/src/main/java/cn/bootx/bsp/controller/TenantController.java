package cn.bootx.bsp.controller;

import cn.bootx.bsp.core.tenant.service.TenantService;
import cn.bootx.bsp.dto.tenant.TenantDto;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.common.core.rest.param.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2020/4/16 21:38
*/
@Api(tags = "租户管理")
@RestController
@RequestMapping("/tenant")
@AllArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @ApiOperation("注册新租户")
    @PostMapping("/register")
    public ResResult<TenantDto> register(TenantDto tenantDto) {
        return Res.ok(tenantService.register(tenantDto));
    }

    @ApiOperation("更新租户信息")
    @PostMapping("/update")
    public ResResult<TenantDto> update(TenantDto tenantDto) {
        return Res.ok(tenantService.update(tenantDto));
    }

    @ApiOperation("更新租户加密类型信息")
    @PostMapping("/updateEncryptModel")
    public ResResult<TenantDto> updateEncryptModel(Long id, Integer encryptModel) {
        return Res.ok(tenantService.updateEncryptModel(id, encryptModel));
    }

    @ApiOperation("租户是否有效")
    @PostMapping("/isTenantValid")
    public ResResult<Boolean> isTenantValid(Long id){
        return Res.ok(tenantService.isTenantValid(id));
    }

    @ApiOperation("激活租户")
    @PostMapping("/activateTenant")
    public ResResult<TenantDto> activateTenant(Long id) {
        return Res.ok(tenantService.activateTenant(id));
    }

    @ApiOperation("禁用租户")
    @PostMapping("/forbiddenTenant")
    public ResResult<TenantDto> forbiddenTenant(Long id) {
        return Res.ok(tenantService.forbiddenTenant(id));
    }

    @ApiOperation("根据Id判断租户是否已存在")
    @GetMapping("/isTenantIdExisted")
    public ResResult<Boolean> isTenantIdExisted(Long id) {
        return Res.ok(tenantService.isTenantIdExisted(id));
    }

    @ApiOperation("根据name判断租户是否已存在")
    @GetMapping("/isTenantNameExisted")
    public ResResult<Boolean> isTenantNameExisted(String name) {
        return Res.ok(tenantService.isTenantNameExisted(name));
    }

    @ApiOperation("根据code判断租户是否已存在")
    @GetMapping("/isTenantCodeExisted")
    public ResResult<Boolean> isTenantCodeExisted(String code) {
        return Res.ok(tenantService.isTenantCodeExisted(code));
    }

    @ApiOperation("根据 id 获取租户信息")
    @GetMapping("/findById")
    public ResResult<TenantDto> findById(Long id) {
        return Res.ok(tenantService.findById(id));
    }

    @ApiOperation("获取租户信息")
    @GetMapping("/findTenant")
    public ResResult<TenantDto> findTenant() {
        return Res.ok(tenantService.findTenant());
    }

    @ApiOperation("根据 名称 获取租户信息")
    @GetMapping("/findByName")
    public ResResult<TenantDto> findByName(String name) {
        return Res.ok(tenantService.findByName(name));
    }

    @ApiOperation("根据 code 获取租户信息")
    @GetMapping("/findByCode")
    public ResResult<TenantDto> findByCode(String code) {
        return Res.ok(tenantService.findByCode(code));
    }

    @ApiOperation("获取所有租户")
    @GetMapping("/findAll")
    public ResResult<List<TenantDto>> findAll() {
        return Res.ok(tenantService.findAll());
    }

    @ApiOperation("根据ID组查找租户")
    @GetMapping("/findByIds")
    public ResResult<List<TenantDto>> findByIds(@RequestParam List<Long> ids) {
        return Res.ok(tenantService.findByIds(ids));
    }

    @ApiOperation("租户分页")
    @GetMapping("/page")
    public ResResult<PageResult<TenantDto>> page(PageParam pageParam) {
        return Res.ok(tenantService.findAll(pageParam));
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public ResResult<Void> delete(Long id) {
        tenantService.deleteById(id);
        return Res.ok();
    }

}