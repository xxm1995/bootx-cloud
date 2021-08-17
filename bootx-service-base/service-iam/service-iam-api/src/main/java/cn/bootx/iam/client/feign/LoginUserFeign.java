package cn.bootx.iam.client.feign;

import cn.bootx.iam.code.IamCode;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = IamCode.APPLICATION_NAME,contextId = "loginUserFeign",path = "/login/user")
public interface LoginUserFeign {

}
