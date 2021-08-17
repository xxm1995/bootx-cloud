package cn.bootx.iam.core.upms.dao;

import cn.bootx.common.core.rest.dto.KeyValue;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.mybatisplus.impl.BaseManager;
import cn.bootx.common.mybatisplus.util.MpUtils;
import cn.bootx.iam.core.upms.entity.Role;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xxm
 * @date 2020/4/28 17:40
 */
@Repository
@RequiredArgsConstructor
public class RoleManager extends BaseManager<RoleMapper,Role> {
    public boolean existsByCode(String code) {
        return existedByField(Role::getCode,code);
    }

    public boolean existsByName(String name) {
        return existedByField(Role::getName,name);
    }

    public boolean existsByCode(String code, Long id) {
        return lambdaQuery().eq(Role::getCode,code)
                .ne(Role::getId,id).exists();
    }

    public boolean existsByName(String name, Long id) {
        return lambdaQuery().eq(Role::getName,name)
                .ne(Role::getId,id).exists();
    }

    public boolean existsById(Long roleId) {
        return lambdaQuery().eq(Role::getId,roleId)
                .exists();
    }

    public List<KeyValue> findDropdown() {
        return lambdaQuery().select(Role::getId,Role::getName)
                .list().stream()
                .map(role -> new KeyValue(String.valueOf(role.getId()),role.getName()))
                .collect(Collectors.toList());

    }

    public com.baomidou.mybatisplus.extension.plugins.pagination.Page<Role> page(PageParam pageParam) {
        Page<Role> mpPage = MpUtils.getMpPage(pageParam, Role.class);
        return lambdaQuery().page(mpPage);
    }
}
