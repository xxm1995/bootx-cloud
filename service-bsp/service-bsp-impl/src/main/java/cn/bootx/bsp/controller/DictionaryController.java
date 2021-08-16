package cn.bootx.bsp.controller;


import cn.bootx.bsp.core.dictionary.service.DictionaryService;
import cn.bootx.bsp.dto.dictionary.DictionaryDto;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2020/4/14 13:40
*/
@Api(tags = "数据字典")
@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
public class DictionaryController {
	private final DictionaryService dictionaryService;

	@PostMapping("/add")
	@ApiOperation("添加")
	public ResResult<DictionaryDto> add(DictionaryDto dictionaryDto) {
		return Res.ok(dictionaryService.add(dictionaryDto));
	}

	@DeleteMapping("/delete")
	@ApiOperation("根据id删除")
	public ResResult<Boolean> delete(Long id) {
        dictionaryService.delete(id);
		return Res.ok();
	}

	@PostMapping("/update")
	@ApiOperation("更新")
	public ResResult<DictionaryDto> update(DictionaryDto dictionaryDto) {
		return Res.ok(dictionaryService.update(dictionaryDto));
	}

	@GetMapping("/get")
	@ApiOperation("根据id获取")
	public ResResult<DictionaryDto> findById(Long id) {
		return Res.ok(dictionaryService.findById(id));
	}

	@GetMapping("/all")
	@ApiOperation("查询全部")
	public ResResult<List<DictionaryDto>> findAll() {
		return Res.ok(dictionaryService.findAll());
	}
}
