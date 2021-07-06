package cn.bootx.usercenter.core.permission.dao;

import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.usercenter.core.permission.entity.Permission;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
public class PermissionManager {
    private final PermissionRepository permissionRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public List<Permission> findByMethodAndServiceName(String method,String serviceName){
        return permissionRepository.findByMethodAndServiceNameAndTid(method,serviceName,headerHolder.getTid());
    }

    public List<Permission> findByIds(List<Long> ids) {
        return permissionRepository.findByIdInAndTid(ids,headerHolder.findTid());
    }

    public List<Permission> findAll() {
        return permissionRepository.findAllByTid(headerHolder.findTid());
    }
}
