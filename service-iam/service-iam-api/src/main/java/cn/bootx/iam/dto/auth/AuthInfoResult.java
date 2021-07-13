package cn.bootx.iam.dto.auth;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**   
* @author xxm
* @date 2021/6/3 
*/
@Data
@Accessors(chain = true)
@ApiModel("认证返回消息")
public class AuthInfoResult {

    /** 用户id */
    private Long uid;
    /** 租户 */
    private Long tid;
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
    /** token */
    private String token;
}