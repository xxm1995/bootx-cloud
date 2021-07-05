package cn.bootx.starter.headerholder.exception;


import cn.bootx.common.web.exception.SystemException;

import java.io.Serializable;

/**   
* 租户未找到
* @author xxm  
* @date 2020/4/22 13:10
*/
public class TenantNotExistedException extends SystemException implements Serializable {

    private static final long serialVersionUID = 5924510852080025935L;

    public TenantNotExistedException() {
		super(100001, "租户未找到");
	}
}
