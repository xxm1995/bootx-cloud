package cn.bootx.bsp.client.feign;

import cn.bootx.bsp.code.BspCode;
import cn.bootx.bsp.dto.channel.ChannelDto;
import cn.bootx.bsp.param.channel.ChannelParam;
import cn.bootx.common.web.rest.ResResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 渠道
* @author xxm
* @date 2021/4/9
*/
@FeignClient(name = BspCode.APPLICATION_NAME,contextId = "channelFeign",path = "channel")
public interface ChannelFeign {

    
    @PostMapping("/add")
    ResResult<ChannelDto> add(@RequestBody ChannelParam param);

    
    @PostMapping("/update")
    ResResult<ChannelDto> update(@RequestBody ChannelParam param);

    
    @PostMapping("/delete")
    ResResult<Void> delete(@RequestParam Long id);

    
    @GetMapping("/findAll")
    ResResult<List<ChannelDto>> findAll();

    
    @GetMapping("/findById")
    ResResult<ChannelDto> findById(@RequestParam Long id);

    
    @GetMapping("/findByType")
    ResResult<List<ChannelDto>> findByType(@RequestParam int typed);

    
    @GetMapping("/findByName")
    ResResult<ChannelDto> findByName(@RequestParam String name);

    
    @GetMapping("/findByKey")
    ResResult<List<ChannelDto>> findByKey(@RequestParam String key);

    
    @PostMapping("/findByIds")
    ResResult<List<ChannelDto>> findByIds(@RequestParam List<Long> ids);
}
