package cn.bootx.paymentcenter.controller;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.paymentcenter.core.payconfig.service.PayChannelService;
import cn.bootx.paymentcenter.dto.payconfig.PayChannelDto;
import cn.bootx.paymentcenter.param.payconfig.PayChannelParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2021/7/1
*/
@Api(tags = "支付渠道")
@RestController
@RequestMapping("/channel")
@RequiredArgsConstructor
public class PayChannelController {
    private final PayChannelService channelService;
    
    @ApiOperation("添加支付渠道")
    @PostMapping("/add")
    public ResResult<PayChannelDto> add(@RequestBody PayChannelParam param){
        return Res.ok(channelService.add(param));
    }

    @ApiOperation("更新支付渠道信息")
    @PostMapping("/update")
    public ResResult<PayChannelDto> update(@RequestBody PayChannelParam param){
        return Res.ok(channelService.update(param));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResResult<PageResult<PayChannelDto>> page(PageParam pageParam, PayChannelParam param){
        return Res.ok(channelService.page(pageParam,param));
    }

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    public ResResult<List<PayChannelDto>> findAll(){
        return Res.ok(channelService.findAll());
    }

    @ApiOperation("获取支付方式编码")
    @GetMapping("/findPayTypeCodes")
    public ResResult<List<String>> findPayTypeCodes(){
        return Res.ok(channelService.findPayTypeCodes());
    }

    @ApiOperation("根据id查询")
    @GetMapping("/findById")
    public ResResult<PayChannelDto> findById(Long id){
        return Res.ok(channelService.findById(id));
    }

    @ApiOperation("根据渠道code判断是否存在")
    @GetMapping("/existsByCode")
    public ResResult<Boolean> existsByCode(String code){
        return Res.ok(channelService.existsByCode(code));
    }

    @ApiOperation("根据渠道code判获取")
    @GetMapping("/findByCode")
    public ResResult<PayChannelDto> findByCode(String code){
        return Res.ok(channelService.findByCode(code));
    }
}
