package cn.bootx.usercenter.client.feign;

import cn.bootx.usercenter.client.DepartClient;
import cn.bootx.usercenter.dto.depart.DepartDto;
import cn.bootx.usercenter.dto.depart.DepartTreeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(DepartClient.class)
public class DepartClientFeignImpl implements DepartClient {
    @Autowired(required = false)
    private DepartFeign departFeign;
    @Override
    public DepartDto add(DepartDto departDto) {
        return departFeign.add(departDto).getData();
    }

    @Override
    public List<DepartTreeResult> tree() {
        return departFeign.tree().getData();
    }

    @Override
    public DepartDto getById(Long id) {
        return departFeign.get(id).getData();
    }

    @Override
    public DepartDto edit(DepartDto departDto) {
        return departFeign.update(departDto).getData();
    }

    @Override
    public void deleteById(Long id) {
        departFeign.remove(id);
    }

    @Override
    public boolean removeAndChildren(Long id) {
        return departFeign.removeAndChildren(id).getData();
    }
}
