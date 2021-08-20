package cn.bootx.bsp.controller;

import cn.bootx.bsp.core.dict.service.DictionaryItemService;
import cn.bootx.bsp.dto.dict.DictionaryItemDto;
import cn.bootx.bsp.param.dict.DictionaryItemParam;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.core.rest.param.SingleIdParam;
import cn.bootx.common.core.util.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* @author xxm
* @date 2020/4/18 19:03
*/
@Api(tags = "字典项")
@RestController
@RequestMapping("/dict/item")
@AllArgsConstructor
public class DictionaryItemController{

	private final DictionaryItemService dictionaryItemService;
    @ApiOperation(value = "添加字典项（返回字典项对象）")
    @PostMapping("/add")
    public ResResult<DictionaryItemDto> add(@RequestBody DictionaryItemParam param) {
        ValidationUtil.validateParam(param);
        return Res.ok(dictionaryItemService.add(param));
    }

    @ApiOperation(value = "删除字典项")
    @DeleteMapping(value = "/delete")
    public ResResult<Void> delete(@RequestBody SingleIdParam param) {
        ValidationUtil.validateParam(param);
        dictionaryItemService.delete(param.getId());
        return Res.ok();
    }

    @ApiOperation(value = "修改字典项（返回字典项对象）")
    @PostMapping(value = "/update")
    public ResResult<DictionaryItemDto> update(@RequestBody DictionaryItemParam param) {
        ValidationUtil.validateParam(param);
        return Res.ok( dictionaryItemService.update(param));
    }

    @ApiOperation(value = "根据字典项ID查询")
    @GetMapping("/findById")
    public ResResult<DictionaryItemDto> findById(@ApiParam("字典项ID")Long id) {
        return Res.ok( dictionaryItemService.findById(id));
    }

    @ApiOperation(value = "查询指定字典ID下的所有字典项")
    @GetMapping("/findByDictionaryId")
    public ResResult<List<DictionaryItemDto>> findByDictionaryId(@ApiParam("字典ID") Long dictionaryId) {
        return Res.ok(dictionaryItemService.findByDictionaryId(dictionaryId));
    }

    @ApiOperation(value = "分页查询指定字典下的字典项")
    @GetMapping("/pageByDictionaryId")
    public ResResult<PageResult<DictionaryItemDto>> pageByDictionaryId(PageParam pageParam, Long dictId) {
        return Res.ok(dictionaryItemService.pageByDictionaryId(dictId,pageParam));
    }
}
