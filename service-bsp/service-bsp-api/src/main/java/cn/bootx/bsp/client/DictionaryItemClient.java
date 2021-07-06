package cn.bootx.bsp.client;

import cn.bootx.bsp.dto.dictionary.DictionaryItemDto;
import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.param.PageParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**   
* 字典项
* @author xxm  
* @date 2020/4/23 22:11
*/
public interface DictionaryItemClient {

	@ApiOperation(value = "添加字典项（返回字典项对象）")
    DictionaryItemDto add(DictionaryItemDto dictionaryItemDto);

	@ApiOperation(value = "删除字典项")
    void delete(Long id);

	@ApiOperation(value = "修改字典项（返回字典项对象）")
	DictionaryItemDto update(DictionaryItemDto dictionaryItemDto);

	@ApiOperation(value = "根据字典项ID查询")
	DictionaryItemDto findById(Long id);

	@ApiOperation(value = "查询指定字典ID下的所有字典项")
	List<DictionaryItemDto> findByDictionaryId(Long dictionaryId);

	@ApiOperation(value = "分页查询指定字典下的字典项")
    PageResult<DictionaryItemDto> pageByDictionaryId(Long dictionaryId,PageParam pageParam);

	@ApiOperation(value = "模糊分页查询")
    PageResult<DictionaryItemDto> search(Long dictionaryId,String word,PageParam pageParam);
}
