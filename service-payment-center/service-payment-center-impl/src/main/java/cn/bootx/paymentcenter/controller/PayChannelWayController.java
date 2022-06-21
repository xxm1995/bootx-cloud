package cn.bootx.paymentcenter.controller;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.paymentcenter.core.payconfig.service.PayChannelWayService;
import cn.bootx.paymentcenter.dto.payconfig.PayChannelWayDto;
import cn.bootx.paymentcenter.param.payconfig.PayChannelWayParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @author xxm
* @date 2021/7/1
*/
@Api(tags = "支付渠道下属支付方式")
@RestController
@RequestMapping("/channel/way")
@RequiredArgsConstructor
public class PayChannelWayController {
    private final PayChannelWayService channelWayService;

    @ApiOperation("添加")
    @PostMapping("/add")
    public ResResult<PayChannelWayDto> add(@RequestBody PayChannelWayParam param){
        return Res.ok(channelWayService.add(param));
    }

    @ApiOperation("获取")
    @GetMapping("/findById")
    public ResResult<PayChannelWayDto> findById(Long id){
        return Res.ok(channelWayService.findById(id));
    }

    @ApiOperation("删除")
    @PostMapping("/delete")
    public ResResult<Void> delete(Long id){
        channelWayService.delete(id);
        return Res.ok();
    }

    @ApiOperation("查询支付通道下的支付方式")
    @GetMapping("/findByChannel")
    public ResResult<List<PayChannelWayDto>> findByChannel(Long channelId){
        return Res.ok(channelWayService.findByChannel(channelId));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public ResResult<PageResult<PayChannelWayDto>> page(PageParam pageParam, PayChannelWayParam param){
        return Res.ok(channelWayService.page(pageParam,param));
    }

}
