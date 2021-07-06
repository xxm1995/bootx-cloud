package cn.bootx.usercenter.core.role.dao;

import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import cn.bootx.usercenter.core.role.entity.QRole;
import cn.bootx.usercenter.core.role.entity.Role;
import com.querydsl.jpa.impl.JPAQuery;
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

    public boolean existsByNameAndCode(String name, String code) {
        return roleRepository.existsByNameAndCodeAndTid(name,code,headerHolder.findTid());
    }

    public boolean existsById(Long id) {
        return roleRepository.existsByIdAndTid(id,headerHolder.findTid());
    }

    public boolean existsByNameAndCode(String name, String code, Long id) {
        return roleRepository.existsByNameAndCodeAndIdNotAndTid(name,code,id,headerHolder.findTid());
    }

    public Optional<Role> findById(Long id) {
        return roleRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public List<Role> findAll() {
        return roleRepository.findAllByTid(headerHolder.findTid());
    }

    public Page<Role> page(PageParam pageParam) {
        QRole q = QRole.role;
        JPAQuery<Role> query = jpaQueryFactory.selectFrom(q);
        return JpaUtils.queryPage(query,pageParam);
    }

    public List<Role> findAllByIds(List<Long> ids) {
        return roleRepository.findAllByIdInAndTid(ids,headerHolder.findTid());
    }
}
