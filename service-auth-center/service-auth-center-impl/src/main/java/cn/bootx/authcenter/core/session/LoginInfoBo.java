package cn.bootx.authcenter.core.session;


import cn.bootx.authcenter.core.auth.convert.AuthConvert;
import cn.bootx.authcenter.dto.UserAuthResult;
import cn.bootx.common.util.LocalDateTimeUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 登录信息值对象
 *
 * @author liuchenwei
 * @date 2018/5/4
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginInfoBo implements Serializable {

    private static final long serialVersionUID = -4856174395070441971L;

    /** 用户id */
    private Long uid;
    /** 租户 */
    private Long tid;
    /** 账号 */
    private String account;
    /** 名称 */
    private String name;
    /** 邮件 */
    private String email;
    /** 手机号 */
    private String phone;
    /** 是否超级管理员 */
    private boolean admin;
    /** 角色id */
    private List<Long> roleIds;

    /** 登录时间 */
    private LocalDateTime loginTime;
    /** 终端 */
    private String client;
    /** 认证code */
    private String authCode;

    public static LoginInfoBo init(Map<String, String> map) {
        Boolean admin = MapUtil.getBool(map, "admin");
        Long loginTimestamp = MapUtil.getLong(map, "loginTime");
        String roleIdsStr = MapUtil.getStr(map, "roleIds");
        List<Long> roleIds = new ArrayList<>(0);
        if (StrUtil.isNotBlank(roleIdsStr)){
            roleIds = StrUtil.split(roleIdsStr, ',').stream()
                    .map(Long::valueOf).collect(Collectors.toList());
        }
        return new LoginInfoBo()
                .setUid(MapUtil.getLong(map,"uid"))
                .setTid(MapUtil.getLong(map,"tid"))
                .setAccount(MapUtil.getStr(map,"account"))
                .setName(MapUtil.getStr(map,"name"))
                .setEmail(MapUtil.getStr(map,"email"))
                .setPhone(MapUtil.getStr(map,"phone"))
                .setAdmin(admin)
                .setRoleIds(roleIds)
                .setLoginTime(LocalDateTimeUtils.parse(loginTimestamp))
                .setClient(MapUtil.getStr(map,"client"))
                .setAuthCode(MapUtil.getStr(map,"authCode"));
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>(11);

        map.put("uid",String.valueOf(uid));
        map.put("tid",String.valueOf(tid));
        map.put("account",account);
        map.put("name",name);
        map.put("email",email);
        map.put("phone",phone);
        map.put("admin",String.valueOf(admin));
        map.put("loginTime",String.valueOf(LocalDateTimeUtils.timestamp(loginTime)));
        map.put("client",client);
        map.put("authCode",authCode);
        if (CollUtil.isNotEmpty(roleIds)){
            map.put("roleIds",CollUtil.join(roleIds,","));
        }
        return map;
    }

    /**
     * 转换成
     */
    public UserAuthResult toResult(){
        return AuthConvert.USER_CONVERT.convert(this);
    }
}
