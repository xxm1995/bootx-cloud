package cn.bootx.paymentcenter.controller;

import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.paymentcenter.core.paymodel.wechat.service.WeChatPayConfigService;
import cn.bootx.paymentcenter.dto.paymodel.wechat.WeChatPayConfigDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
* @author xxm
* @date 2021/3/19
*/
@Api(tags = "微信支付")
@RestController
@RequestMapping("/wx")
@AllArgsConstructor
public class WeChatController {
    private final WeChatPayConfigService weChatPayConfigService;

    @ApiOperation("添加微信支付配置")
    @PostMapping("/add")
    public ResResult<WeChatPayConfigDto> add(@RequestBody WeChatPayConfigDto param){
        return Res.ok(weChatPayConfigService.add(param));
    }
    @ApiOperation("更新")
    @PostMapping("/update")
    public ResResult<WeChatPayConfigDto> update(@RequestBody WeChatPayConfigDto param){
        return Res.ok(weChatPayConfigService.update(param));
    }

    @ApiOperation("根据商户应用Id进行删除")
    @PostMapping("/deleteByAppId")
    public ResResult<Void> deleteByAppId(String appId){
        weChatPayConfigService.deleteByAppId(appId);
        return Res.ok();
    }

    @ApiOperation("根据商户应用AppId查询")
    @GetMapping("/findByAppId")
    public ResResult<WeChatPayConfigDto> findByAppId(String appId){
        return Res.ok(weChatPayConfigService.findByAppId(appId));
    }

}
