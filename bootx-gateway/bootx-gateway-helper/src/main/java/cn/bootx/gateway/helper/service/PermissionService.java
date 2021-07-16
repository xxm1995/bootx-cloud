package cn.bootx.gateway.helper.service;

import cn.bootx.gateway.helper.domain.Permission;
import cn.bootx.gateway.helper.domain.RequestKey;
import cn.bootx.usercenter.client.PermissionClient;
import cn.bootx.usercenter.dto.permission.PermissionDto;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * 权限
 * @author xxm
 * @date 2021/6/7
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionService {
    private PermissionClient permissionClient;
    private final ExecutorService asyncExecutorService;

    private final AntPathMatcher matcher = new AntPathMatcher();

    private void init(){
        if (Objects.isNull(permissionClient)){
            permissionClient = SpringUtil.getBean(PermissionClient.class);
        }
    }

    /**
     * 先通过method和service从数据库中查询权限；
     * 如果匹配到多条权限，则排序计算出匹配度最高的权限
     */
    public Permission selectPermissionByRequest(RequestKey requestKey){
        String uri = requestKey.getUri();
        List<Permission> permissions = this.selectPermissions(requestKey.getServiceName(), requestKey.getMethod());
        // 筛选出最适合的权限
        List<Permission> matchPermissions = permissions.stream()
                .filter(t -> matcher.match(t.getPath(), uri))
                .sorted((o1, o2) -> {
                    Comparator<String> patternComparator = matcher.getPatternComparator(uri);
                    return patternComparator.compare(o1.getPath(), o2.getPath());
                }).collect(Collectors.toList());
        int matchSize = matchPermissions.size();
        if (matchSize < 1) {
            return null;
        } else {
            Permission bestMatchPermission = matchPermissions.get(0);
            if (matchSize > 1) {
                log.info("请求：{} 匹配到的权限：{}，最佳匹配是：{}",uri, matchPermissions, bestMatchPermission.getPath());
            }
            return bestMatchPermission;
        }
    }

    /**
     * 查询权限
     */
    private List<Permission> selectPermissions(String serviceName, String method) {
        this.init();
        String serviceNameFinal = StringUtils.lowerCase(serviceName);
        String methodFinal = StringUtils.lowerCase(method);

        // 异步转同步(filter中无法使用同步阻塞方法)
        List<PermissionDto> permissionDtos;
        try {
            permissionDtos = asyncExecutorService.submit(() -> permissionClient.findByMethodAndService(methodFinal, serviceNameFinal)).get();
        } catch (InterruptedException | ExecutionException e) {
            log.warn("调用IAM失败",e);
            return new ArrayList<>(0);
        }
        if (CollUtil.isEmpty(permissionDtos)){
            return new ArrayList<>(0);
        }
        return permissionDtos.parallelStream()
                .map(o -> {
                    Permission permission = new Permission();
                    BeanUtil.copyProperties(o, permission);
                    return permission;
                }).collect(Collectors.toList());
    }
}
