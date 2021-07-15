package cn.bootx.iam.core.permission.dao;

import cn.bootx.iam.core.permission.entity.PermissionPath;
import cn.bootx.starter.headerholder.HeaderHolder;
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
public class PermissionPathManager {
    private final PermissionPathRepository repository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public List<PermissionPath> findByMethodAndServiceName(String method, String serviceName){
        return repository.findByMethodAndServiceNameAndTid(method,serviceName,headerHolder.getTid());
    }

    public List<PermissionPath> findByIds(List<Long> ids) {
        return repository.findByIdInAndTid(ids,headerHolder.findTid());
    }

    public List<PermissionPath> findAll() {
        return repository.findAllByTid(headerHolder.findTid());
    }
}
