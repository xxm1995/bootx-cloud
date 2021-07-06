package cn.bootx.gateway.helper.context;

import cn.bootx.gateway.helper.domain.CheckState;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
/**   
* 响应上下文
* @author xxm  
* @date 2021/5/31 
*/
@Data
@Accessors(chain = true)
public class ResponseContext {
    /** http状态 */
    private HttpStatus httpStatus;

    /** 检查状态 */
    private CheckState checkState;

    /** jwtToken */
    private String jwtToken;

    /** 请求状态 */
    private String requestStatus;

    /** 响应状态 */
    private String requestCode;

    /** 请求消息 */
    private String requestMessage;
}
