package cn.bootx.bsp.client;

import cn.bootx.bsp.dto.dictionary.DictionaryDto;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**   
* 字典
* @author xxm  
* @date 2020/4/13 16:36
*/
public interface DictionaryClient {

	@ApiOperation("添加字典")
    DictionaryDto add(DictionaryDto dictionaryDto);

	@ApiOperation("删除字典")
	void delete(Long id);

	@ApiOperation("修改字典")
	DictionaryDto update(DictionaryDto dictionaryDto);

	@ApiOperation("查询指定字典")
	DictionaryDto findById(Long id);

	@ApiOperation("查询所有字典")
	List<DictionaryDto> findAll();
}
