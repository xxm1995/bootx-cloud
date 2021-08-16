package cn.bootx.iam.core.role.dao;

import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.iam.core.role.entity.Role;
import cn.bootx.iam.param.role.RoleParam;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import cn.hutool.core.util.StrUtil;
import com.querydsl.jpa.impl.JPAQuery;
import cn.bootx.iam.core.role.entity.QRole;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xxm
 * @date 2020/4/28 17:40
 */
@Repository
@RequiredArgsConstructor
public class RoleManager {
    private final RoleRepository roleRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public boolean existsByCode(String code) {
        return roleRepository.existsByCodeAndTid(code,headerHolder.findTid());
    }


    public boolean existsByName(String name) {
        return roleRepository.existsByNameAndTid(name,headerHolder.findTid());
    }

    public boolean existsByCode(String code, Long id) {
        return roleRepository.existsByCodeAndIdNotAndTid(code,id,headerHolder.findTid());
    }

    public boolean existsByName(String name, Long id) {
        return roleRepository.existsByNameAndIdNotAndTid(name,id,headerHolder.findTid());
    }

    public boolean existsById(Long id) {
        return roleRepository.existsByIdAndTid(id,headerHolder.findTid());
    }


    public Optional<Role> findById(Long id) {
        return roleRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public List<Role> findAll() {
        return roleRepository.findAllByTid(headerHolder.findTid());
    }

    public Page<Role> page(PageParam pageParam, RoleParam param) {
        QRole q = QRole.role;
        JPAQuery<Role> query = jpaQueryFactory.selectFrom(q);
        if (StrUtil.isNotBlank(param.getCode())){
            query.where(q.code.like("%"+param.getCode()+"%"));
        }
        if (StrUtil.isNotBlank(param.getName())){
            query.where(q.name.like("%"+param.getName()+"%"));
        }
        return JpaUtils.queryPage(query,pageParam);
    }

    public List<Role> findByIds(List<Long> ids) {
        return roleRepository.findAllByIdInAndTid(ids,headerHolder.findTid());
    }
}
