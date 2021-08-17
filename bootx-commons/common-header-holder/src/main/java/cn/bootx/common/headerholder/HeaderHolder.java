package cn.bootx.common.headerholder;

import cn.bootx.common.core.code.WebHeaderConst;
import cn.bootx.common.headerholder.exception.OperatorNotExistedException;
import cn.bootx.common.headerholder.exception.TenantNotExistedException;
import cn.bootx.common.headerholder.exception.TokenNotExistedException;
import cn.bootx.common.headerholder.local.HolderContextHolder;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

/**
 * 请求头获取工具类
 * @author xxm
 * @date 2020/4/14 15:23
 */
@UtilityClass
public class HeaderHolder {

    public String findAccessToken() {
        return Optional.ofNullable(getAccessToken()).orElseThrow(TokenNotExistedException::new);
    }

    public String getAccessToken() {
        return getHeader(WebHeaderConst.ACCESS_TOKEN);
    }

    public String findJwtToken(){
        return Optional.ofNullable(getJwtToken()).orElseThrow(TokenNotExistedException::new);
    }

    public String getJwtToken() {
        return getHeader(WebHeaderConst.JWT_TOKEN);
    }

    /**
     * 获取租户id
     */
    public Long findTid(){
        return Optional.ofNullable(getTid()).orElseThrow(TenantNotExistedException::new);
    }

    public Long getTid() {
        String header = getHeader(WebHeaderConst.TID);
        return  StrUtil.isBlank(header) ? null : Long.parseLong(header);
    }

    public String getOpToken() {
        return getHeader(WebHeaderConst.OP_TOKEN);
    }


    public Long findOperatorId(){
        return Optional.ofNullable(getOperatorId()).orElseThrow(OperatorNotExistedException::new);

    }

    public Long getOperatorId(){
        String header = getHeader(WebHeaderConst.OPERATOR_ID);
        return StrUtil.isBlank(header) ? null : Long.parseLong(header);
    }

    public Long findChannelId() {
        return Optional.ofNullable(getChannelId()).orElseThrow(OperatorNotExistedException::new);
    }

    public Long getChannelId() {
        String header = getHeader(WebHeaderConst.CHANNEL_ID);
        return StrUtil.isBlank(header) ? null : Long.parseLong(header);
    }

    /**
     * 获取请求头参数
     */
    public String getHeader(String name){

        // TTL
        String ttlHeader = HolderContextHolder.get(name);
        if (Objects.nonNull(ttlHeader)){
            return ttlHeader;
        }

        // web
        String webHeader = getWebHeader(name);
        if (Objects.nonNull(webHeader)){
            return webHeader;
        }
        return null;
    }

    /**
     * 测web上下文中获取请求头参数
     */
    private String getWebHeader(String name) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        if (requestAttributes instanceof ServletRequestAttributes){
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return request.getHeader(name);
        }
        return null;
    }
}

