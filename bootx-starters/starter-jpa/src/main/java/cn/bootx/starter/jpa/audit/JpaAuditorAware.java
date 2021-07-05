package cn.bootx.starter.jpa.audit;

import cn.bootx.common.web.code.CommonCode;
import cn.bootx.common.web.entity.CustomUserDetails;
import cn.bootx.starter.auth.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 创建修改人审计字段
 * @author xxm
 * @date 2020/9/1
 */
@RequiredArgsConstructor
public class JpaAuditorAware implements AuditorAware<Long> {
    private final SecurityUtils securityUtils;

    @SuppressWarnings("NullableProblems")
    @Override
    public Optional<Long> getCurrentAuditor() {
        Long operatorId = Optional.ofNullable(securityUtils.getUserDetails())
                .map(CustomUserDetails::getUserId)
                .orElse(CommonCode.SYSTEM_DEFAULT_USERID);
        return Optional.of(operatorId);

    }
}
