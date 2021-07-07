package cn.bootx.paymentcenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
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
    private final PayChannelService payChannelService;
    
    @ApiOperation("添加支付渠道")
    @PostMapping("/add")
    public ResResult<PayChannelDto> add(@RequestBody PayChannelParam param){
        return Res.ok(payChannelService.add(param));
    }

    @ApiOperation("更新支付渠道信息")
    @PostMapping("/update")
    public ResResult<PayChannelDto> update(@RequestBody PayChannelParam param){
        return Res.ok(payChannelService.update(param));
    }

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    public ResResult<List<PayChannelDto>> findAll(){
        return Res.ok(payChannelService.findAll());
    }

    @ApiOperation("获取支付方式编码")
    @GetMapping("/findPayTypeCodes")
    public ResResult<List<String>> findPayTypeCodes(){
        return Res.ok(payChannelService.findPayTypeCodes());
    }

    @ApiOperation("根据id查询")
    @GetMapping("/findById")
    public ResResult<PayChannelDto> findById(Long id){
        return Res.ok(payChannelService.findById(id));
    }

    @ApiOperation("根据渠道code判断是否存在")
    @GetMapping("/existsByCode")
    public ResResult<Boolean> existsByCode(String code){
        return Res.ok(payChannelService.existsByCode(code));
    }

    @ApiOperation("根据渠道code判获取")
    @GetMapping("/findByCode")
    public ResResult<PayChannelDto> findByCode(String code){
        return Res.ok(payChannelService.findByCode(code));
    }
}
