package cn.bootx.engine.shop.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.engine.shop.core.address.service.UserAddressService;
import cn.bootx.engine.shop.dto.address.UserAddressDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xxm
* @date 2021/2/17
*/
@Api(tags = "用户收货地址")
@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class UserAddressController {
    private final UserAddressService userAddressService;

    @ApiOperation("添加")
    @PostMapping("/add")
    public ResResult<UserAddressDto> add(UserAddressDto param){
        return Res.ok(userAddressService.add(param));
    }
}
