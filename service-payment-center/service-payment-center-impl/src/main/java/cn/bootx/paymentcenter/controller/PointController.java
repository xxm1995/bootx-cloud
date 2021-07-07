package cn.bootx.paymentcenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.paymentcenter.core.paymodel.point.service.PointService;
import cn.bootx.paymentcenter.param.paymodel.point.PointParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
* @author xxm
* @date 2020/12/11
*/
@Api(tags = "积分服务")
@RestController
@RequestMapping("/point")
@AllArgsConstructor
public class PointController {
    private final PointService pointService;

    @ApiOperation("生产积分")
    @PostMapping("/addPoint")
    public ResResult<Void> addPoint(@RequestBody PointParam param){
        pointService.addPoint(param);
        return Res.ok();
    }
    
    @ApiOperation("获取可以积分")
    @GetMapping("/getAvailablePoint")
    public ResResult<Integer> getAvailablePoint(Long userId){
        return Res.ok(pointService.getAvailablePoint(userId));
    }

    @ApiOperation("摧毁积分")
    @PostMapping("/destroyPoint")
    public ResResult<Void> destroyPoint(@RequestBody PointParam pointParam){
        pointService.destroyPoint(pointParam);
        return Res.ok();
    }
}
