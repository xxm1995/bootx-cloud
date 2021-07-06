package cn.bootx.bsp.client.feign;

import cn.bootx.bsp.code.BspCode;
import cn.bootx.bsp.dto.tenant.TenantDto;
import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.rest.param.PageParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 租户
 * @author xxm
 * @date 2021/4/9
 */
@FeignClient(name = BspCode.APPLICATION_NAME,contextId = "tenantFeign",path = "tenant")
public interface TenantFeign {

    @PostMapping("/register")
    ResResult<TenantDto> register(@RequestBody TenantDto tenantDto);


    @PostMapping("/update")
    ResResult<TenantDto> update(@RequestBody TenantDto tenantDto);


    @PostMapping("/updateEncryptModel")
    ResResult<TenantDto> updateEncryptModel(@RequestParam Long id,@RequestParam Integer encryptModel);

    @PostMapping("/isTenantValid")
    ResResult<Boolean> isTenantValid(@RequestParam Long id);

    @PostMapping("/activateTenant")
    ResResult<TenantDto> activateTenant(@RequestParam Long id);


    @PostMapping("/forbiddenTenant")
    ResResult<TenantDto> forbiddenTenant(@RequestParam Long id);


    @GetMapping("/isTenantIdExisted")
    ResResult<Boolean> isTenantIdExisted(@RequestParam Long id);


    @GetMapping("/isTenantNameExisted")
    ResResult<Boolean> isTenantNameExisted(@RequestParam String name);


    @GetMapping("/isTenantCodeExisted")
    ResResult<Boolean> isTenantCodeExisted(@RequestParam String code);

    @ApiOperation("获取租户信息")
    @GetMapping("/findTenant")
    ResResult<TenantDto> findTenant();

    @GetMapping("/findById")
    ResResult<TenantDto> findById(@RequestParam Long id);

    @GetMapping("/findByName")
    ResResult<TenantDto> findByName(@RequestParam String name);


    @GetMapping("/findByCode")
    ResResult<TenantDto> findByCode(@RequestParam String code);


    @GetMapping("/findAll")
    ResResult<List<TenantDto>> findAll();


    @GetMapping("/findTenantsByIds")
    ResResult<List<TenantDto>> findByIds(@RequestParam List<Long> ids);


    @GetMapping("/page")
    ResResult<PageResult<TenantDto>> page(@RequestBody PageParam pageParam);


    @PostMapping("/delete")
    ResResult<Void> delete(@RequestParam Long id);

}
