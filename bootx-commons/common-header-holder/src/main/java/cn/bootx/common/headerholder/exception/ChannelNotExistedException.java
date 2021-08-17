package cn.bootx.common.headerholder.exception;

import cn.bootx.common.core.exception.SystemException;

import java.io.Serializable;

/**   
* 渠道未找到
* @author xxm  
* @date 2021/3/9 
*/
public class ChannelNotExistedException extends SystemException implements Serializable {
    private static final long serialVersionUID = -8254231934467918201L;

    public ChannelNotExistedException() {
        super(100003, "渠道未找到");
    }

}
