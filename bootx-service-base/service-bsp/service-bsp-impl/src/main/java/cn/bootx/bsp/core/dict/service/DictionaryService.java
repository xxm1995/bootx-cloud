package cn.bootx.bsp.core.dict.service;

import cn.bootx.bsp.core.dict.dao.DictionaryItemManager;
import cn.bootx.bsp.core.dict.dao.DictionaryManager;
import cn.bootx.bsp.core.dict.entity.Dictionary;
import cn.bootx.bsp.dto.dict.DictionaryDto;
import cn.bootx.bsp.exception.dict.DictAlreadyExistedException;
import cn.bootx.bsp.exception.dict.DictItemAlreadyUsedException;
import cn.bootx.bsp.exception.dict.DictNotExistedException;
import cn.bootx.bsp.param.dict.DictionaryParam;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.mybatisplus.util.MpUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author xxm
* @date 2020/4/10 15:52
*/
@Service
@AllArgsConstructor
public class DictionaryService {

	private final DictionaryManager dictionaryManager;
	private final DictionaryItemManager dictionaryItemManager;

	/**
	 * 添加字典
	 */
	@Transactional(rollbackFor = Exception.class)
	public DictionaryDto add(DictionaryParam param) {
		if (dictionaryManager.existsByName(param.getName())) {
			throw new DictAlreadyExistedException();
		}
		Dictionary dictionary = Dictionary.init(param);
		return dictionaryManager.save(dictionary).toDto();
	}

	/**
	 * 删除字典
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {

		if(dictionaryManager.existedById(id)) {
			throw new DictNotExistedException();
		}
		if (dictionaryItemManager.existsByDictId(id)){
            throw new DictItemAlreadyUsedException();
        }
        dictionaryManager.deleteById(id);
	}

    /**
     * 更新
     */
	@Transactional(rollbackFor = Exception.class)
	public DictionaryDto update(DictionaryParam param) {

        Dictionary dictionary = dictionaryManager.findById(param.getId())
                .orElseThrow(DictNotExistedException::new);

		// 判断字典是否重名
		if (dictionaryManager.existsByNameAndIdNot(param.getName(),param.getId())) {
			throw new DictAlreadyExistedException();
		}
		// 更新字典项
        BeanUtil.copyProperties(param,dictionary, CopyOptions.create().ignoreNullValue());
        dictionaryItemManager.updateDictCode(dictionary.getId(),dictionary.getCode());
        return dictionaryManager.updateById(dictionary).toDto();
	}

	/**
	 * 查询指定字典
	 */
	public DictionaryDto findById(Long id) {
		return dictionaryManager.findById(id).map(Dictionary::toDto).orElse(null);
	}

	/**
	 * 查询所有字典
	 */
	public List<DictionaryDto> findAll() {
		List<Dictionary> dictionaries = dictionaryManager.findAll();
		return dictionaries.stream()
                .map(Dictionary::toDto)
                .collect(Collectors.toList());
	}

    /**
     * 查询所有字典
     */
    public PageResult<DictionaryDto> page(PageParam pageParam, DictionaryParam param) {
        return MpUtils.convert2PageResult(dictionaryManager.page(pageParam,param));
    }
}
