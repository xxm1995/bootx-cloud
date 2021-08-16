package cn.bootx.starter.headerholder.holder;

import cn.bootx.common.core.code.WebHeaderConst;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.headerholder.exception.OperatorNotExistedException;
import cn.bootx.starter.headerholder.exception.TenantNotExistedException;
import cn.bootx.starter.headerholder.exception.TokenNotExistedException;
import cn.bootx.starter.headerholder.local.HolderContextHolder;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

/**   
* 默认请求头获取工具
* @author xxm  
* @date 2020/11/7 
*/
public class DefaultHeaderHolder implements HeaderHolder {

    @Override
    public String findAccessToken() {
        return Optional.ofNullable(this.getAccessToken()).orElseThrow(TokenNotExistedException::new);
    }

    @Override
    public String getAccessToken() {
        return this.getHeader(WebHeaderConst.ACCESS_TOKEN);
    }

    @Override
    public String findJwtToken(){
        return Optional.ofNullable(this.getJwtToken()).orElseThrow(TokenNotExistedException::new);
    }

    @Override
    public String getJwtToken() {
        return this.getHeader(WebHeaderConst.JWT_TOKEN);
    }

    /**
     * 获取租户id
     */
    @Override
    public Long findTid(){
        return Optional.ofNullable(this.getTid()).orElseThrow(TenantNotExistedException::new);
    }

    @Override
    public Long getTid() {
        String header = this.getHeader(WebHeaderConst.TID);
        return  StrUtil.isBlank(header) ? null : Long.parseLong(header);
    }

    @Override
    public String getOpToken() {
        return this.getHeader(WebHeaderConst.OP_TOKEN);
    }


    @Override
    public Long findOperatorId(){
        return Optional.ofNullable(this.getOperatorId()).orElseThrow(OperatorNotExistedException::new);

    }

    @Override
    public Long getOperatorId(){
        String header = this.getHeader(WebHeaderConst.OPERATOR_ID);
        return StrUtil.isBlank(header) ? null : Long.parseLong(header);
    }

    @Override
    public Long findChannelId() {
        return Optional.ofNullable(this.getChannelId()).orElseThrow(OperatorNotExistedException::new);
    }

    @Override
    public Long getChannelId() {
        String header = this.getHeader(WebHeaderConst.CHANNEL_ID);
        return StrUtil.isBlank(header) ? null : Long.parseLong(header);
    }

    /**
     * 获取请求头参数
     */
    @Override
    public String getHeader(String name){

        // TTL
        String ttlHeader = HolderContextHolder.get(name);
        if (Objects.nonNull(ttlHeader)){
            return ttlHeader;
        }

        // web
        String webHeader = this.getWebHeader(name);
        if (Objects.nonNull(webHeader)){
            return webHeader;
        }
        return null;
    }

    protected String getWebHeader(String name) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getHeader(name);
    }
}
