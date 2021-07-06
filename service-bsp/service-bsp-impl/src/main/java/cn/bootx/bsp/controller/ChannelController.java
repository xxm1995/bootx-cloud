package cn.bootx.bsp.controller;

import cn.bootx.bsp.core.channel.service.ChannelService;
import cn.bootx.bsp.dto.channel.ChannelDto;
import cn.bootx.bsp.param.channel.ChannelParam;
import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2020/11/19
*/
@Api(tags = "渠道管理")
@RestController
@RequestMapping("/channel")
@AllArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @ApiOperation("添加渠道")
    @PostMapping("/add")
    public ResResult<ChannelDto> add(@RequestBody ChannelParam param){
        return Res.ok(channelService.add(param));
    }

    @ApiOperation("修改渠道")
    @PostMapping("/update")
    public ResResult<ChannelDto> update(@RequestBody ChannelParam param){
        return Res.ok(channelService.update(param));
    }

    @ApiOperation("删除渠道")
    @PostMapping("/delete")
    public ResResult<Void> delete(Long id){
        channelService.delete(id);
        return Res.ok();
    }

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    public ResResult<List<ChannelDto>> findAll(){
        return Res.ok(channelService.findAll());
    }

    @ApiOperation("查询单条")
    @GetMapping("/findById")
    public ResResult<ChannelDto> findById(Long id){
        return Res.ok(channelService.findById(id));
    }

    @ApiOperation("根据type查询")
    @GetMapping("/findByType")
    public ResResult<List<ChannelDto>> findByType(int typed){
        return Res.ok(channelService.findByType(typed));
    }

    @ApiOperation("根据name查询")
    @GetMapping("/findByName")
    public ResResult<ChannelDto> findByName(String name){
        return Res.ok(channelService.findByName(name));
    }

    @ApiOperation("根据name查询")
    @GetMapping("/getByKey")
    public ResResult<List<ChannelDto>> findByKey(String key){
        return Res.ok(channelService.findByKey(key));
    }

    @ApiOperation("根据Ids查询")
    @PostMapping("/findByIds")
    public ResResult<List<ChannelDto>> findByIds(List<Long> ids){
        return Res.ok(channelService.findByIds(ids));
    }
}
