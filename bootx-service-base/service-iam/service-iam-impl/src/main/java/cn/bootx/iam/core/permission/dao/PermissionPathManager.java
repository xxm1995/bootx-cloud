package cn.bootx.iam.core.permission.dao;

import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.iam.core.permission.entity.PermissionPath;
import cn.bootx.iam.core.permission.entity.QPermissionPath;
import cn.bootx.iam.param.permission.PermissionPathParam;
import cn.bootx.common.headerholder.HeaderHolder;
import cn.bootx.common.jpa.utils.JpaUtils;
import cn.hutool.core.util.StrUtil;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Page<PermissionPath> page(PageParam pageParam, PermissionPathParam param) {
        QPermissionPath q = QPermissionPath.permissionPath;
        JPAQuery<PermissionPath> query = jpaQueryFactory.selectFrom(q);

        if (StrUtil.isNotBlank(param.getPath())){
            query.where(q.path.like("%"+param.getPath()+"%"));
        }
        if (StrUtil.isNotBlank(param.getServiceName())){
            query.where(q.serviceName.like("%"+param.getServiceName()+"%"));
        }
        if (StrUtil.isNotBlank(param.getMethod())){
            query.where(q.method.eq(param.getMethod()));
        }
        if (StrUtil.isNotBlank(param.getCode())){
            query.where(q.code.like("%"+param.getCode()+"%"));
        }
        return JpaUtils.queryPage(query,pageParam);
    }

    public Optional<PermissionPath> findById(Long id) {
        return repository.findByIdAndTid(id,headerHolder.findTid());
    }

    public void deleteById(Long id) {
        repository.deleteByIdAndTid(id,headerHolder.findTid());
    }
}
