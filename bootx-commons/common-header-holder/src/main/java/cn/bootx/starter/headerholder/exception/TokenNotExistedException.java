package cn.bootx.starter.headerholder.exception;


import cn.bootx.common.core.exception.SystemException;

import java.io.Serializable;

/**   
* token未找到
* @author xxm  
*/
public class TokenNotExistedException extends SystemException implements Serializable {

    private static final long serialVersionUID = -2759477362963117195L;

    public TokenNotExistedException() {
		super(100002, "token未找到");
	}
}
