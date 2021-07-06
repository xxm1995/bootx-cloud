package cn.bootx.usercenter.client.feign;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.usercenter.client.RoleClient;
import cn.bootx.usercenter.dto.role.RoleDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(RoleClient.class)
public class RoleClientFeignImpl implements RoleClient {
    @Override
    public RoleDto addRole(RoleDto roleDto) {
        return null;
    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public RoleDto updateRole(RoleDto roleDto) {
        return null;
    }

    @Override
    public List<RoleDto> findAll() {
        return null;
    }

    @Override
    public PageResult<RoleDto> page(PageParam pageParam) {
        return null;
    }

    @Override
    public RoleDto getById(Long id) {
        return null;
    }
}
