package cn.bootx.bsp.client.feign;

import cn.bootx.bsp.code.BspCode;
import cn.bootx.bsp.dto.dictionary.DictionaryDto;
import cn.bootx.common.core.rest.ResResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 字典
* @author xxm
* @date 2021/4/11
*/
@FeignClient(name = BspCode.APPLICATION_NAME,contextId = "dictionaryFeign",path = "/dictionary")
public interface DictionaryFeign {

    @PostMapping("/add")
    @ApiOperation("添加")
    ResResult<DictionaryDto> add(DictionaryDto dictionaryDto) ;

    @DeleteMapping("/delete")
    @ApiOperation("根据id删除")
    ResResult<Boolean> delete(@RequestParam Long id) ;

    @PostMapping("/update")
    @ApiOperation("更新")
    ResResult<DictionaryDto> update(@RequestBody DictionaryDto dictionaryDto) ;

    @GetMapping("/findById")
    @ApiOperation("根据id获取")
    ResResult<DictionaryDto> findById(@RequestParam Long id) ;

    @GetMapping("/all")
    @ApiOperation("查询全部")
    ResResult<List<DictionaryDto>> findAll() ;
}
