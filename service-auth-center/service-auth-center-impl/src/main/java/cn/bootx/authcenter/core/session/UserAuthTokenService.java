package cn.bootx.authcenter.core.session;


import cn.bootx.authcenter.config.configuration.PropertyValueProperties;
import cn.bootx.authcenter.dto.UserAuthResult;
import cn.bootx.common.web.exception.ValidationFailedException;
import cn.bootx.starter.jackson.utils.JacksonUtils;
import cn.bootx.starter.redis.RedisClient;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 用户认证 session 服务
 * 
 * Redis 来管理维护用户登录 session 信息。同一个用户可以从不同的客户端登录不同的租户，
 * 默认情况下，登录状态维持半个小时，每次调用 通过 token 获取 uid 接口时，
 * 将自动重设用户登录状态在半小时后失效。
 * 
 * 使用两个数据结构来实现 session 管理，一个是 session 表结构，另一个是 online 表结构。
 * session 表结构使用 Hash，其 key 模式 user:session:{token}
 * 该表结构用来管理 session，主要提供通过 token 获取 uid 的功能。
 * online 表结构使用 String，该表结构用来解决重复登录的问题，主要提供通过 uid 获取 session 的功能。
 * 其 key 模式 user:online:{tid}:{client}:{uid}
 *
 * @author network
 */
@Component
@AllArgsConstructor
public class UserAuthTokenService {

    private static final String SPLITTER = ":";
    private static final String KEY_PREFIX_TOKEN = "ac:token:";
    private static final String KEY_PREFIX_ONLINE = "ac:online:";
    private static final String KEY_PREFIX_CLIENT = "ac:client:";


    private final TokenGenerator tokenGenerator;
    private final RedisClient redisClient;
    private final PropertyValueProperties propertyValueProperties;

    /**
     * 新增 session，返回 token
     */
    public UserAuthResult addAuth(LoginInfoBo loginInfo) {
        Long uid = loginInfo.getUid();
        Long tid = loginInfo.getTid();
        String client = loginInfo.getClient();
        this.validation(tid, uid, client);

        // 处理 会话用户信息(session) 表
        String token = tokenGenerator.gen();
        String sessionKey = this.constructKey4Token(token);
        redisClient.setWithTimeout(sessionKey,JacksonUtils.toJson(loginInfo),propertyValueProperties.getSessionTimeout());

        // 处理 租户终端(client) 表
        String key4Client = this.constructKey4Client(tid);
        Map<String, String> tenantClientMap = this.mergeValue4Client(key4Client, client);
        redisClient.hmSet(key4Client, tenantClientMap);
        redisClient.expire(key4Client, propertyValueProperties.getClientTimeout());

        // 处理 online 表
        String key4Online = this.constructKey4Online(tid, uid, client);
        String key4Session2 = redisClient.get(key4Online);

        // 意味着重复登录，删除之前的 session
        if (!StrUtil.isEmpty(key4Session2)) {
            redisClient.deleteKey(key4Session2);
        }
        redisClient.setWithTimeout(key4Online, sessionKey, propertyValueProperties.getSessionTimeout());
        return loginInfo.toResult().setToken(token);
    }

    /**
     * 通过 token 获取登录角色
     */
    public LoginInfoBo getLoginInfo(String token) {
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        // 会话用户
        String key4Session = constructKey4Token(token);
        LoginInfoBo loginInfoBo = Optional.ofNullable(redisClient.get(key4Session))
                .map(s -> JacksonUtils.toBean(s,LoginInfoBo.class))
                .orElse(null);
        if (Objects.isNull(loginInfoBo)) {
            return null;
        }
        // 会话用户角色
        if (loginInfoBo.getUid() == null) {
            return null;
        }
        // 每访问一次 session 就增加默认时效
        redisClient.expire(key4Session, propertyValueProperties.getSessionTimeout());

        // 每访问一次 online 就增加默认时效
        String key4Online = this.constructKey4Online(loginInfoBo.getTid(), loginInfoBo.getUid(), loginInfoBo.getClient());
        redisClient.expire(key4Online, propertyValueProperties.getSessionTimeout());
        return loginInfoBo;
    }

    /**
     * 移除 token 对应的 session
     */
    public void remove(String token) {
        validation(token);
        String key4Session = constructKey4Token(token);
        redisClient.deleteKey(key4Session);
    }

    private void validation(Object... params) {
        if (params != null) {
            for (Object param : params) {
                if (Objects.isNull(param)) {
                    throw new ValidationFailedException("参数不能为空");
                }
            }
        }
    }

    /**
     * 生成token的key
     */
    private String constructKey4Token(String token) {
        return KEY_PREFIX_TOKEN + token;
    }

    /**
     * 在线表的key
     */
    private String constructKey4Online(Long tid, Long uid, String client) {
        return KEY_PREFIX_ONLINE + tid + SPLITTER + client + SPLITTER + uid;
    }

    /**
     * 终端表的key
     */
    private String constructKey4Client(Long tid) {
        return KEY_PREFIX_CLIENT + tid;
    }

    /**
     * 合并终端表中的值
     */
    private Map<String, String> mergeValue4Client(String key, String client) {
        Map<String, String> tenantClientMap = redisClient.hmGet(key);
        tenantClientMap.put(client, client);
        return tenantClientMap;
    }

    /**
     * 获取租户表的key值
     */
    private Set<String> getTenantClientKeys(Long tid) {
        String tenantClientKey = this.constructKey4Client(tid);
        Map<String, String> tenantClientMap = redisClient.hmGet(tenantClientKey);
        return tenantClientMap.keySet();
    }

    /**
     * 踢出用户
     */
    public void kickedOutUser(Long tid, Long uid) {
        Set<String> clientKeys = this.getTenantClientKeys(tid);
        if (CollectionUtil.isEmpty(clientKeys)) {
            return;
        }

        Set<String> keys = Sets.newHashSetWithExpectedSize(clientKeys.size());
        for (String client : clientKeys) {
            keys.add(this.constructKey4Online(tid, uid, client));
        }

        if (CollectionUtil.isEmpty(keys)) {
            return;
        }
        List<String> tokens = redisClient.get(keys);
        tokens.removeAll(Collections.singleton(null));
        if (!CollectionUtil.isEmpty(tokens)) {
            redisClient.deleteKeys(tokens);
            redisClient.deleteKeys(keys);
        }
    }

}
