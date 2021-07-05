package cn.bootx.starter.headerholder.exception;


import cn.bootx.common.web.exception.SystemException;

import java.io.Serializable;

/**   
* Operator未找到
* @author xxm  
*/
public class OperatorNotExistedException extends SystemException implements Serializable {

    private static final long serialVersionUID = -2759477362963117195L;

    public OperatorNotExistedException() {
		super(100003, "Operator未找到");
	}
}
