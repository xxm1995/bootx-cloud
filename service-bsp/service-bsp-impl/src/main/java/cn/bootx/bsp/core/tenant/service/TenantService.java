package cn.bootx.bsp.core.tenant.service;

import cn.bootx.bsp.core.tenant.dao.TenantManager;
import cn.bootx.bsp.core.tenant.dao.TenantRepository;
import cn.bootx.bsp.core.tenant.entity.Tenant;
import cn.bootx.bsp.dto.tenant.TenantDto;
import cn.bootx.bsp.exception.tenant.TenantAlreadyExistedException;
import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.headerholder.exception.TenantNotExistedException;
import cn.bootx.starter.jpa.utils.JpaUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 租户处理
 * @author xxm
 * @date 2020/4/16 21:50
 */
@Slf4j
@Service
@AllArgsConstructor
public class TenantService {
    private final TenantRepository tenantRepository;
    private final HeaderHolder headerHolder;
    private final TenantManager tenantManager;

    /**
     * 注册新租户
     */
    @Transactional(rollbackFor = Exception.class)
    public TenantDto register(TenantDto tenantDto) {
        // 租户名称不允许重复
        if (tenantRepository.existsByName(tenantDto.getName())) {
            throw new TenantAlreadyExistedException();
        }
        Tenant tenant = Tenant.init(tenantDto);
        return tenantRepository.save(tenant).toDto();
    }

    /**
     * 更新租户基本信息
     */
    @Transactional(rollbackFor = Exception.class)
    public TenantDto update(TenantDto tenantDto) {
        Tenant tenant = tenantRepository.findById(tenantDto.getId())
                .orElseThrow(TenantNotExistedException::new);

        // 租户名称不允许重复
        if (tenantRepository.existsByNameAndIdNot(tenantDto.getName(),tenantDto.getId())) {
            throw new TenantAlreadyExistedException();
        }

        BeanUtil.copyProperties(tenantDto,tenant, CopyOptions.create().ignoreNullValue());
        TenantDto dto = tenantRepository.save(tenant).toDto();
        tenantManager.deleteCache(dto.getId());
        return dto;
    }

    /**
     * 更新租户加密类型信息
     */
    @Transactional(rollbackFor = Exception.class)
    public TenantDto updateEncryptModel(Long id, Integer encryptModel) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(TenantNotExistedException::new);
        tenant.setEncryptModel(encryptModel);
        TenantDto tenantDto = tenantRepository.save(tenant).toDto();
        tenantManager.deleteCache(id);
        return tenantDto;
    }

    /**
     * 租户是否有效
     */
    public boolean isTenantValid(Long tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId).orElse(null);
        return tenant != null && Objects.equals(TenantDto.ACTIVE_STATE_YES, tenant.getActiveState());
    }

    /**
     * 激活租户
     */
    @Transactional(rollbackFor = Exception.class)
    public TenantDto activateTenant(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(TenantNotExistedException::new);
        tenant.setActiveState(TenantDto.ACTIVE_STATE_YES)
                .setActiveDate(LocalDateTime.now());
        TenantDto tenantDto = tenantRepository.save(tenant).toDto();
        tenantManager.deleteCache(id);
        return tenantDto;
    }

    /**
     * 禁用租户
     */
    @Transactional(rollbackFor = Exception.class)
    public TenantDto forbiddenTenant(Long id) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(TenantNotExistedException::new);
        tenant.setActiveState(TenantDto.ACTIVE_STATE_NO)
                .setActiveDate(LocalDateTime.now());
        TenantDto tenantDto = tenantRepository.save(tenant).toDto();
        tenantManager.deleteCache(id);
        return tenantDto;
    }

    /**
     * 判断租户是否已存在
     */
    public boolean isTenantIdExisted(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        return tenantRepository.existsById(id);
    }

    /**
     * 判断租户name是否已存在
     */
    public boolean isTenantNameExisted(String name) {
        if (StrUtil.isEmpty(name)) {
            return false;
        }
        return tenantRepository.existsByName(name);
    }

    /**
     * 判断租户code是否已存在
     */
    public boolean isTenantCodeExisted(String code) {
        if (StrUtil.isEmpty(code)) {
            return false;
        }
        return tenantRepository.existsByCode(code);
    }

    /**
     * 根据数据库信息获取租户
     */
    public TenantDto findById(Long id){
        TenantDto tenantDto = tenantManager.get(id);
        if (Objects.nonNull(tenantDto)){
            return tenantDto;
        }
        Optional<Tenant> tenant = tenantRepository.findById(id);
        TenantDto dto = tenant.map(Tenant::toDto).orElse(null);
        tenantManager.addCache(dto);
        return dto;
    }

    /**
     * 获取租户
     */
    public TenantDto findTenant(){
        return this.findById(headerHolder.findTid());
    }

    /**
     * 根据 名称 查询
     */
    public TenantDto findByName(String name) {
        Optional<Tenant> tenant = tenantRepository.findByName(name);
        return tenant.map(Tenant::toDto).orElse(null);
    }
    /**
     * 根据 code 查询
     */
    public TenantDto findByCode(String code) {
        Optional<Tenant> tenant = tenantRepository.findByCode(code);
        return tenant.map(Tenant::toDto).orElse(null);
    }

    /**
     * 根据ID组查找租户
     */
    public List<TenantDto> findByIds(List<Long> ids) {
        List<Tenant> tenants = tenantRepository.findAllById(ids);
        return tenants.stream().map(Tenant::toDto).collect(Collectors.toList());
    }

    /**
     * 获取所有租户
     */
    public List<TenantDto> findAll() {
        List<Tenant> tenants = tenantRepository.findAll();
        return tenants.stream().map(Tenant::toDto).collect(Collectors.toList());
    }

    /**
     * 分页获取租户
     */
    public PageResult<TenantDto> findAll(PageParam pageParam) {
        Pageable jpaPage = JpaUtils.getPageable(pageParam);
        return  JpaUtils.convert2PageResult(tenantRepository.findAll(jpaPage));
    }

    /**
     * 删除租户
     */
    public void deleteById(Long id) {
        if (tenantRepository.existsById(id)){
            tenantRepository.deleteById(id);
        }
    }
}
