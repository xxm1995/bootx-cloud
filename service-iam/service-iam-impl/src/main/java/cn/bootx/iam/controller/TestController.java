package cn.bootx.iam.controller;

import cn.bootx.iam.core.role.service.RoleMenuService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试控制器")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final RoleMenuService roleMenuService;

}
