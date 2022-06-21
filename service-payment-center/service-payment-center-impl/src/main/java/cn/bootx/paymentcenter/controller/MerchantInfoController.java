package cn.bootx.paymentcenter.controller;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.paymentcenter.core.merchant.service.MerchantInfoService;
import cn.bootx.paymentcenter.dto.merchant.MerchantInfoDto;
import cn.bootx.paymentcenter.param.merchant.MerchantInfoParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xxm
 * @date 2021/6/29
 */
@Api(tags = "商户管理")
@RestController
@RequestMapping("/merchant")
@RequiredArgsConstructor
public class MerchantInfoController {
    private final MerchantInfoService merchantInfoService;

    @ApiOperation("添加商户")
    @PostMapping("/add")
    public ResResult<MerchantInfoDto> add(@RequestBody MerchantInfoParam param){
        return Res.ok(merchantInfoService.add(param));
    }

    @ApiOperation("修改商户")
    @PostMapping("/update")
    public ResResult<MerchantInfoDto> update(@RequestBody MerchantInfoParam param){
        return Res.ok(merchantInfoService.update(param));
    }

    @ApiOperation("查询详情")
    @GetMapping("/findById")
    public ResResult<MerchantInfoDto> findById(Long id){
        return Res.ok(merchantInfoService.findById(id));
    }

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    public ResResult<List<MerchantInfoDto>> findAll(){
        return Res.ok(merchantInfoService.findAll());
    }
    @ApiOperation("分页")
    @GetMapping("/page")
    public ResResult<PageResult<MerchantInfoDto>> page(PageParam pageParam, MerchantInfoParam param){
        return Res.ok(merchantInfoService.page(pageParam,param));
    }
}
