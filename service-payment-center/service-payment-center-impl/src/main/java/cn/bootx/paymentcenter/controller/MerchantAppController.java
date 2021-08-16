package cn.bootx.paymentcenter.controller;

import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.paymentcenter.core.merchant.service.MerchantAppService;
import cn.bootx.paymentcenter.dto.merchant.MerchantAppDto;
import cn.bootx.paymentcenter.dto.payconfig.PayChannelDto;
import cn.bootx.paymentcenter.param.merchant.MerchantAppParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2021/6/29
*/
@Api(tags = "商户应用管理")
@RestController
@RequestMapping("/merchant/app")
@RequiredArgsConstructor
public class MerchantAppController {
    private final MerchantAppService merchantAppService;

    @ApiOperation("添加商户应用")
    @PostMapping("/add")
    public ResResult<MerchantAppDto> add(@RequestBody MerchantAppParam param){
        return Res.ok(merchantAppService.add(param));
    }

    @ApiOperation("查询详情")
    @GetMapping("/findById")
    public ResResult<MerchantAppDto> findById(Long id){
        return Res.ok(merchantAppService.findById(id));
    }

    @ApiOperation("查询商户下的应用")
    @GetMapping("/findByMerchantId")
    public ResResult<List<MerchantAppDto>> findByMerchantId(Long merchantId){
        return Res.ok(merchantAppService.findByMerchantId(merchantId));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public ResResult<PageResult<MerchantAppDto>> page(PageParam pageParam, MerchantAppParam param){
        return Res.ok(merchantAppService.page(pageParam,param));
    }

    @ApiOperation("查询应用下的支付通道配置列表")
    @GetMapping("/findAppChannels")
    public ResResult<List<PayChannelDto>> findAppChannels(String appId){
        return Res.ok(merchantAppService.findAppChannels(appId));
    }
    
}
