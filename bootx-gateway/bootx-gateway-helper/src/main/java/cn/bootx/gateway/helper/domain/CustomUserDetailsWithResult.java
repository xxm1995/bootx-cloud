package cn.bootx.gateway.helper.domain;

import cn.bootx.common.core.entity.CustomUserDetails;
import lombok.Data;
import lombok.experimental.Accessors;

/**   
* 用户信息和返回状态
* @author xxm  
* @date 2021/6/1 
*/
@Data
@Accessors(chain = true)
public class CustomUserDetailsWithResult {
    private static final long serialVersionUID = -2022325583037285394L;
    /** 用户信息 */
    private CustomUserDetails customUserDetails;

    /** 状态 */
    private CheckState state;

    /** 错误消息 */
    private String message;
}
