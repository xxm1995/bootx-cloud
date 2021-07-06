package cn.bootx.bsp.core.tenant.dao;


import cn.bootx.bsp.core.tenant.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant,Long> {
    /** 根据用户名查询重复 */
    boolean existsByName(String name);
    /** 根据用户名查询重复 排除id */
    boolean existsByNameAndIdNot(String name,Long id);
    /** 根据code查询重复  */
    boolean existsByCode(String code);
    /** 根据名称查询 */
    Optional<Tenant> findByName(String name);
    /** 根据code查询 */
    Optional<Tenant> findByCode(String code);
}
