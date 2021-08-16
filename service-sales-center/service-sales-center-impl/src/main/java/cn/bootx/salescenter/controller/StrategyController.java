package cn.bootx.salescenter.controller;

import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.salescenter.core.strategy.service.StrategyService;
import cn.bootx.salescenter.dto.strategy.StrategyConfigDto;
import cn.bootx.salescenter.dto.strategy.StrategyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2020/10/17
*/
@Api(tags = "策略")
@RestController
@RequestMapping("/strategy")
@AllArgsConstructor
public class StrategyController {
    private final StrategyService strategyService;

    @ApiOperation("添加策略")
    @PostMapping("/add")
    public ResResult<StrategyDto> add(@RequestBody StrategyDto param){
        return Res.ok(strategyService.addStrategy(param));
    }

    @ApiOperation("查询全部策略")
    @GetMapping("/findAll")
    public ResResult<List<StrategyDto>> findAll(){
        return Res.ok(strategyService.findAll());
    }

    @ApiOperation("获取策略及其配置项")
    @GetMapping("/get/{id}")
    public ResResult<StrategyDto> get(@PathVariable Long id){
        return Res.ok(strategyService.getById(id));
    }

    @ApiOperation("获取参数配置")
    @GetMapping("/findConfigs")
    public ResResult<List<StrategyConfigDto>> findConfigs(Long id){
        return Res.ok(strategyService.findConfigsByStrategyId(id));
    }

    @ApiOperation("根据类型查询")
    @GetMapping("/findByType")
    public ResResult<List<StrategyConfigDto>> findByType(Integer type){
        return Res.ok(strategyService.findByType(type));
    }

}
