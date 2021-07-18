package cn.bootx.gateway.helper.service;

import cn.bootx.bsp.client.TenantClient;
import cn.bootx.bsp.dto.tenant.TenantDto;
import cn.bootx.gateway.helper.domain.CheckState;
import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Slf4j
@Service
@RequiredArgsConstructor
public class TenantService {
    private final ExecutorService asyncExecutorService;
    private TenantClient tenantClient;

    private void init(){
        if (Objects.isNull(tenantClient)){
            tenantClient = SpringUtil.getBean(TenantClient.class);
        }
    }
    /**
     * 获取
     */
    public TenantResult checkTenant() {
        this.init();
        TenantDto tenant;
        try {
            tenant = asyncExecutorService.submit(() ->
                    tenantClient.findTenant()).get();
        } catch (InterruptedException | ExecutionException e) {
            log.warn("获取租户信息失败",e);
            return new TenantResult()
                    .setState(CheckState.TENANT_IS_EMPTY)
                    .setMessage("获取租户信息失败");
        }
        if (Objects.isNull(tenant)){
            return new TenantResult()
                    .setState(CheckState.TENANT_IS_EMPTY)
                    .setMessage("租户信息为空");
        }
        if (tenant.getActiveState() == TenantDto.ACTIVE_STATE_NO){
            return new TenantResult()
                    .setState(CheckState.TENANT_IS_DISABLED)
                    .setMessage("当前租户不可用");
        }
        return new TenantResult().setFlag(true);
    }

    @Data
    @Accessors(chain = true)
    public static class TenantResult {
        /** 是否通过审核 */
        boolean flag;

        /** 状态 */
        private CheckState state;

        /** 错误消息 */
        private String message;
    }
}
