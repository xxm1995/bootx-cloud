package cn.bootx.gateway.helper.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**   
* 检查响应
* @author xxm  
* @date 2021/6/1 
*/
@Data
@Accessors(chain = true)
public class CheckResponse {
    /** jwt */
    private String jwt;
    /** 消息体 */
    private String message;
    /** 检查状态 */
    private CheckState status;

}
