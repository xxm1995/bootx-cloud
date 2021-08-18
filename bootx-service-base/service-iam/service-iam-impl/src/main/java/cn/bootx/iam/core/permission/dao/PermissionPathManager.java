package cn.bootx.iam.core.permission.dao;

import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.mybatisplus.impl.BaseManager;
import cn.bootx.common.mybatisplus.util.MpUtils;
import cn.bootx.iam.core.permission.entity.PermissionPath;
import cn.bootx.iam.param.permission.PermissionPathParam;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限
* @author xxm
* @date 2020/5/10 23:27
*/
@Repository
@RequiredArgsConstructor
public class PermissionPathManager extends BaseManager<PermissionPathMapper,PermissionPath> {

    public List<PermissionPath> findByMethodAndServiceName(String method, String serviceName){
        return lambdaQuery().eq(PermissionPath::getMethod,method)
                .eq(PermissionPath::getServiceName,serviceName)
                .list();
    }

    public Page<PermissionPath> page(PageParam pageParam, PermissionPathParam param) {

        Page<PermissionPath> mpPage = MpUtils.getMpPage(pageParam, PermissionPath.class);

        return lambdaQuery()
                .eq(StrUtil.isNotBlank(param.getPath()),PermissionPath::getPath,param.getPath())
                .eq(StrUtil.isNotBlank(param.getMethod()),PermissionPath::getMethod,param.getMethod())
                .eq(StrUtil.isNotBlank(param.getCode()),PermissionPath::getCode,param.getCode())
                .page(mpPage);

    }
}
