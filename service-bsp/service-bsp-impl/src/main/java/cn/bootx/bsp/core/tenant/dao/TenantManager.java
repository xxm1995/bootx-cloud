package cn.bootx.bsp.core.tenant.dao;

import cn.bootx.bsp.dto.tenant.TenantDto;
import cn.bootx.starter.jackson.utils.JacksonUtils;
import cn.bootx.starter.redis.RedisClient;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**   
* 租户管理
* @author xxm  
* @date 2020/11/12 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class TenantManager {
    private final TenantRepository tenantRepository;
    private final RedisClient redisClient;

    private final String PREFIX_KEY = "bsp:tenant:";
    private final long TIME_OUT = 1000*60*12*60;

    /**
     * 添加缓存
     */
    public void addCache(TenantDto param){
        if (Objects.isNull(param)){
            return;
        }
        String json = JacksonUtils.toJson(param);
        redisClient.setWithTimeout(PREFIX_KEY+param.getId(),json,TIME_OUT);
    }

    /**
     * 获取缓存
     */
    public TenantDto get(Long tid){
        String json = redisClient.get(PREFIX_KEY+tid);
        if (StrUtil.isNotBlank(json)){
            return JacksonUtils.toBean(json,TenantDto.class);
        }
        return null;
    }

    /**
     * 删除缓存
     */
    public void deleteCache(Long tid){
        redisClient.deleteKey(PREFIX_KEY+tid);
    }
}
