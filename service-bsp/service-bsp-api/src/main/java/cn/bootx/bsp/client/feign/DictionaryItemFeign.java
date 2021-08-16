package cn.bootx.bsp.client.feign;

import cn.bootx.bsp.code.BspCode;
import cn.bootx.bsp.dto.dictionary.DictionaryItemDto;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.common.core.rest.param.PageParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* 字典项
* @author xxm  
* @date 2021/4/11 
*/
@FeignClient(name = BspCode.APPLICATION_NAME,contextId = "dictionaryItemFeign",path = "/dictionary/item")
public interface DictionaryItemFeign {

    @ApiOperation(value = "添加字典项（返回字典项对象）")
    @PostMapping("/add")
    ResResult<DictionaryItemDto> add(@RequestBody DictionaryItemDto dictionaryItemDto);

    @ApiOperation(value = "删除字典项")
    @PostMapping("/delete")
    ResResult<Void> delete(@RequestParam Long id);

    @ApiOperation(value = "修改字典项（返回字典项对象）")
    @PostMapping("/update")
    ResResult<DictionaryItemDto> update(@RequestBody DictionaryItemDto dictionaryItemDto);

    @ApiOperation(value = "根据字典项ID查询")
    @GetMapping("/findById")
    ResResult<DictionaryItemDto> findById(@RequestParam Long id);

    @ApiOperation(value = "查询指定字典ID下的所有字典项")
    @GetMapping("/findByDictionaryId")
    ResResult<List<DictionaryItemDto>> findByDictionaryId(@RequestParam Long dictionaryId);

    @ApiOperation(value = "分页查询指定字典下的字典项")
    @GetMapping("/pageByDictionaryId")
    ResResult<PageResult<DictionaryItemDto>> pageByDictionaryId(@RequestParam Long dictionaryId,@RequestBody PageParam pageParam);

    @ApiOperation(value = "模糊分页查询")
    @GetMapping("/search")
    ResResult<PageResult<DictionaryItemDto>> search(@RequestParam Long dictionaryId,@RequestParam String word,@RequestBody PageParam pageParam);
}
