package cn.bootx.gateway.helper.api;

import cn.bootx.gateway.helper.context.RequestContext;
import cn.bootx.gateway.helper.context.ResponseContext;
import cn.bootx.gateway.helper.domain.CheckResponse;
import cn.bootx.gateway.helper.domain.CheckState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * gatewayHelper链
 * @author xxm
 * @date 2021/5/31
 */
@Slf4j
@Component
@RequiredArgsConstructor
public final class HelperChain implements ApplicationContextAware {

    private List<HelperFilter> helperFilters;

    /**
     * 初始化鉴权过滤器
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Collection<HelperFilter> values = applicationContext.getBeansOfType(HelperFilter.class).values();
        this.helperFilters = values
                .stream()
                .sorted(Comparator.comparing(HelperFilter::filterOrder))
                .collect(Collectors.toList());
    }

    /**
     * 未经授权的状态
     */
    private static final Set<CheckState> UNAUTHORIZED = new HashSet<>(Arrays.asList(
            CheckState.PERMISSION_ACCESS_TOKEN_NULL,
            CheckState.PERMISSION_ACCESS_TOKEN_INVALID,
            CheckState.PERMISSION_ACCESS_TOKEN_EXPIRED,
            CheckState.PERMISSION_GET_USE_DETAIL_FAILED
    ));

    /**
     * 执行鉴权过滤器
     */
    public ResponseContext doFilter(RequestContext requestContext) {
        CheckResponse checkResponse = requestContext.response;
        ResponseContext responseContext = new ResponseContext();
        try {
            for (HelperFilter helperFilter : helperFilters) {
                // true则继续执行后面的filter，false不再执行
                if (helperFilter.shouldFilter(requestContext) && !helperFilter.run(requestContext)) {
                    break;
                }
            }
        } catch (Exception e) {
            checkResponse.setStatus(CheckState.EXCEPTION_GATEWAY_HELPER);
            checkResponse.setMessage("网关助手发生错误: " + e);
            log.info("网关助手发生错误", e);
        }
        // 成功
        if (checkResponse.getStatus().getValue() < HttpStatus.MULTIPLE_CHOICES.value()) {
            responseContext.setHttpStatus(HttpStatus.OK);
            log.debug("成功 200, context: {}", requestContext);
        }
        // 未认证
        else if (UNAUTHORIZED.contains(checkResponse.getStatus())) {
            responseContext.setHttpStatus(HttpStatus.UNAUTHORIZED);
            log.info("未认证 401, context: {}", requestContext.getResponse().getMessage());
        }
        // 禁止访问
        else if (checkResponse.getStatus().getValue() < HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            responseContext.setHttpStatus(HttpStatus.FORBIDDEN);
            log.info("禁止访问 403, context: {}", requestContext.getResponse().getMessage());
        }
        // 服务器错误
        else {
            responseContext.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            log.info("服务器错误 500, context: {}", requestContext.getResponse().getMessage());
        }

        // 提示信息
        if (Objects.nonNull(checkResponse.getMessage())) {
            responseContext.setRequestMessage(checkResponse.getMessage());
        }
        // 给响应上下文添加数据
        responseContext.setJwtToken(checkResponse.getJwt());
        responseContext.setRequestStatus(checkResponse.getStatus().getName());
        responseContext.setRequestCode(checkResponse.getStatus().getCode());
        return responseContext;
    }

}
