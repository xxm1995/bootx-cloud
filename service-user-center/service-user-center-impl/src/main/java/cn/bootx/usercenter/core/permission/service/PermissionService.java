package cn.bootx.usercenter.core.permission.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.bootx.common.web.exception.BizException;
import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.usercenter.core.permission.dao.PermissionManager;
import cn.bootx.usercenter.core.permission.dao.PermissionRepository;
import cn.bootx.usercenter.core.permission.entity.Permission;
import cn.bootx.usercenter.core.permission.entity.PermissionImport;
import cn.bootx.usercenter.dto.permission.PermissionDto;
import cn.bootx.usercenter.param.permission.PermissionParam;
import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static cn.bootx.usercenter.code.CachingCode.PERMISSION_GATEWAY;

/**
 * 权限资源
 * @author xxm
 * @date 2020/5/10 23:20
 */
@Service
@AllArgsConstructor
public class PermissionService {
    private final PermissionManager permissionManager;
    private final PermissionRepository permissionRepository;

    /**
     * 根据http方式和服务名进行查询
     */
    @Cacheable(value = PERMISSION_GATEWAY,key = "#serviceName+':'+#method")
    public List<PermissionDto> findByMethodAndService(String method,String serviceName){
        return ResultConvertUtils.dtoListConvert(permissionManager.findByMethodAndServiceName(method,serviceName));
    }

    /**
     * 添加权限信息
     */
    @CacheEvict(value = PERMISSION_GATEWAY,key = "#param.serviceName+':'+#param.method")
    public PermissionDto add(PermissionParam param){
        Permission permission = Permission.init(param);
        // code去重
        return permissionRepository.save(permission).toDto();
    }

    /**
     * 更新权限信息
     */
    @CacheEvict(value = PERMISSION_GATEWAY,allEntries = true)
    public void update(){

    }

    /**
     * 删除
     */
    @CacheEvict(value = PERMISSION_GATEWAY,allEntries = true)
    public void delete(Long id){
    }

    /**
     * 查询权限信息
     */
    public List<PermissionDto> findByIds(List<Long> ids){
        return ResultConvertUtils.dtoListConvert(permissionManager.findByIds(ids));
    }

    /**
     * 导入
     */
    public void importPermission(MultipartFile file) {
        try {
            ImportParams params = new ImportParams();
            List<PermissionImport> objects = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    PermissionImport.class, params);

            List<Permission> collect = objects.stream()
                    .map(permissionImport -> {
                        Permission permission = new Permission();
                        BeanUtil.copyProperties(permissionImport, permission);
                        return permission;
                    }).collect(Collectors.toList());
            permissionRepository.saveAll(collect);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("无法读取文件");
        }
    }

    public List<PermissionDto> list() {
        return ResultConvertUtils.dtoListConvert(permissionManager.findAll());
    }
}
