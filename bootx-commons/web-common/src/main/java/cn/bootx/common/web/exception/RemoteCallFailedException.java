package cn.bootx.common.web.exception;

import java.io.Serializable;

import static cn.bootx.common.web.rest.CommonErrorCodes.REMOTE_CALL_ERROR;

/**
* 远程调用失败异常
* @author xxm
* @date 2021/4/7
*/
public class RemoteCallFailedException extends SystemException implements Serializable {
    private static final long serialVersionUID = -1834006061590071523L;

    public RemoteCallFailedException(){
        super(REMOTE_CALL_ERROR,"远程调用出错");
    }

    public RemoteCallFailedException(String msg){
        super(REMOTE_CALL_ERROR,msg);
    }
}
