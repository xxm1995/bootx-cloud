package cn.bootx.bsp.controller;

import cn.bootx.bsp.core.dictionary.service.DictionaryItemService;
import cn.bootx.bsp.dto.dictionary.DictionaryItemDto;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.core.rest.param.SingleIdParam;
import cn.bootx.common.core.util.ValidationUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* @author xxm
* @date 2020/4/18 19:03
*/
@Api(tags = "字典项")
@RestController
@RequestMapping("/dictionary/item")
@AllArgsConstructor
public class DictionaryItemController{

	private final DictionaryItemService dictionaryItemService;

	@ApiOperation(value = "添加字典项（返回字典项对象）")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResResult<DictionaryItemDto> add(@RequestBody DictionaryItemDto dictionaryItemDto) {
		ValidationUtil.validateParam(dictionaryItemDto);
		return Res.ok(dictionaryItemService.add(dictionaryItemDto));
	}


	@ApiOperation(value = "删除字典项")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResResult<Void> delete(@RequestBody SingleIdParam param) {
		ValidationUtil.validateParam(param);
        dictionaryItemService.delete(param.getId());
		return Res.ok();
	}

	@ApiOperation(value = "修改字典项（返回字典项对象）")
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResResult<DictionaryItemDto> update(@RequestBody DictionaryItemDto dictionaryItemDto) {
		ValidationUtil.validateParam(dictionaryItemDto);
		return Res.ok( dictionaryItemService.update(dictionaryItemDto));
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
	public ResResult<PageResult<DictionaryItemDto>> pageByDictionaryId(PageParam pageParam, Long dictionaryId) {
		return Res.ok(dictionaryItemService.pageByDictionaryId(dictionaryId,pageParam));
	}

	@ApiOperation(value = "模糊分页查询")
	@GetMapping("/search")
	public ResResult<PageResult<DictionaryItemDto>> search(Long dictionaryId,String word,PageParam pageParam) {
		return Res.ok(dictionaryItemService.search( dictionaryId, StrUtil.trim(word),pageParam));
	}
}
