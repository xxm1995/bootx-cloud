package cn.bootx.iam.client.feign;

import cn.bootx.common.core.entity.UserDetail;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.iam.code.IamCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**   
* 会话
* @author xxm  
* @date 2021/8/18 
*/
@FeignClient(name = IamCode.APPLICATION_NAME,contextId = "sessionFeign",path = "/session")
public interface SessionFeign {

    @ApiOperation("根据用户id获取请求权限id(列表)")
    @GetMapping("/getUserDetail")
    ResResult<UserDetail> getUserDetail();
}
