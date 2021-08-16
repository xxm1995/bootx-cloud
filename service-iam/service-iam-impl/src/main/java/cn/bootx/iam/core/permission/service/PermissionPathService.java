package cn.bootx.iam.core.permission.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.bootx.common.core.exception.BizException;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.core.util.ResultConvertUtils;
import cn.bootx.iam.core.permission.dao.PermissionPathManager;
import cn.bootx.iam.core.permission.dao.PermissionPathRepository;
import cn.bootx.iam.core.permission.dto.PermissionPathImport;
import cn.bootx.iam.core.permission.entity.PermissionPath;
import cn.bootx.iam.dto.permission.PermissionPathDto;
import cn.bootx.iam.param.permission.PermissionPathParam;
import cn.bootx.starter.jpa.utils.JpaUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static cn.bootx.iam.code.CachingCode.PERMISSION_PATH;


/**
 * 请求权限
 * @author xxm
 * @date 2020/5/10 23:20
 */
@Service
@AllArgsConstructor
public class PermissionPathService {
    private final PermissionPathManager permissionPathManager;
    private final PermissionPathRepository permissionPathRepository;

    /**
     * 根据http方式和服务名进行查询
     */
    @Cacheable(value = PERMISSION_PATH,key = "#serviceName+':'+#method")
    public List<PermissionPathDto> findByMethodAndService(String method, String serviceName){
        return ResultConvertUtils.dtoListConvert(permissionPathManager.findByMethodAndServiceName(method,serviceName));
    }

    /**
     * 添加权限信息
     */
    @CacheEvict(value = PERMISSION_PATH,key = "#param.serviceName+':'+#param.method")
    public PermissionPathDto add(PermissionPathParam param){
        PermissionPath permissionPath = PermissionPath.init(param);
        // code去重
        return permissionPathRepository.save(permissionPath).toDto();
    }

    /**
     * 更新权限信息
     */
    @CacheEvict(value = PERMISSION_PATH,allEntries = true)
    public PermissionPathDto update(PermissionPathParam param){
        PermissionPath permissionPath = permissionPathManager.findById(param.getId())
                .orElseThrow(() -> new BizException("信息不存在"));
        BeanUtil.copyProperties(param,permissionPath,CopyOptions.create().ignoreNullValue());
        return permissionPathRepository.save(permissionPath).toDto();
    }

    /**
     * 删除
     */
    @CacheEvict(value = PERMISSION_PATH,allEntries = true)
    public void delete(Long id){
        permissionPathManager.deleteById(id);
    }

    /**
     * 获取单条
     */
    public PermissionPathDto findById(Long id){
        return permissionPathManager.findById(id).map(PermissionPath::toDto)
                .orElse(null);
    }

    /**
     * 根据ids查询权限信息
     */
    public List<PermissionPathDto> findByIds(List<Long> ids){
        return ResultConvertUtils.dtoListConvert(permissionPathManager.findByIds(ids));
    }

    /**
     * 列表
     */
    public List<PermissionPathDto> list() {
        return ResultConvertUtils.dtoListConvert(permissionPathManager.findAll());
    }

    /**
     * 权限分页
     */
    public PageResult<PermissionPathDto> page(PageParam pageParam,PermissionPathParam param){
        Page<PermissionPath> page = permissionPathManager.page(pageParam,param);
        return JpaUtils.convert2PageResult(page);
    }

    /**
     * 导入
     */
    public void importPermission(MultipartFile file) {
        try {
            ImportParams params = new ImportParams();
            List<PermissionPathImport> objects = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    PermissionPathImport.class, params);

            List<PermissionPath> collect = objects.stream()
                    .map(permissionImport -> {
                        PermissionPath permissionPath = new PermissionPath();
                        BeanUtil.copyProperties(permissionImport, permissionPath);
                        return permissionPath;
                    }).collect(Collectors.toList());
            permissionPathRepository.saveAll(collect);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("无法读取文件");
        }
    }
}
