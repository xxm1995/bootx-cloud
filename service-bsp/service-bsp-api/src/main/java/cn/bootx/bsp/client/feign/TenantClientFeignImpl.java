package cn.bootx.bsp.client.feign;

import cn.bootx.bsp.client.TenantClient;
import cn.bootx.bsp.dto.tenant.TenantDto;
import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.param.PageParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* 租户
* @author xxm
* @date 2021/4/11
*/
@Component
@RequiredArgsConstructor
public class TenantClientFeignImpl implements TenantClient {

    @Autowired(required = false)
    private TenantFeign tenantFeign;
    @Override
    public TenantDto register(TenantDto tenantDto) {
        return tenantFeign.register(tenantDto).getData();
    }

    @Override
    public TenantDto update(TenantDto tenantDto) {
        return tenantFeign.update(tenantDto).getData();
    }

    @Override
    public TenantDto updateEncryptModel(Long id, Integer encryptModel) {
        return tenantFeign.updateEncryptModel(id,encryptModel).getData();
    }

    @Override
    public boolean isTenantValid(Long tenantId) {
        return tenantFeign.isTenantValid(tenantId).getData();
    }

    @Override
    public TenantDto activateTenant(Long id) {
        return tenantFeign.activateTenant(id).getData();
    }

    @Override
    public TenantDto forbiddenTenant(Long id) {
        return tenantFeign.forbiddenTenant(id).getData();
    }

    @Override
    public boolean isTenantIdExisted(Long id) {
        return tenantFeign.isTenantIdExisted(id).getData();
    }

    @Override
    public boolean isTenantNameExisted(String name) {
        return tenantFeign.isTenantNameExisted(name).getData();
    }

    @Override
    public boolean isTenantCodeExisted(String code) {
        return tenantFeign.isTenantCodeExisted(code).getData();
    }

    @Override
    public TenantDto findById(Long id) {
        return tenantFeign.findById(id).getData();
    }

    @Override
    public TenantDto findTenant() {
        return tenantFeign.findTenant().getData();
    }

    @Override
    public TenantDto findByName(String name) {
        return tenantFeign.findByName(name).getData();
    }

    @Override
    public TenantDto findByCode(String code) {
        return tenantFeign.findByCode(code).getData();
    }

    @Override
    public List<TenantDto> findByIds(List<Long> ids) {
        return tenantFeign.findByIds(ids).getData();
    }

    @Override
    public List<TenantDto> findAll() {
        return tenantFeign.findAll().getData();
    }

    @Override
    public PageResult<TenantDto> page(PageParam pageParam) {
        return tenantFeign.page(pageParam).getData();
    }

    @Override
    public void delete(Long id) {
        tenantFeign.delete(id);
    }
}
