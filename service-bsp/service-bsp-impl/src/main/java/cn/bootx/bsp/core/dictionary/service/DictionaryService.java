package cn.bootx.bsp.core.dictionary.service;

import cn.bootx.bsp.core.dictionary.dao.DictionaryManager;
import cn.bootx.bsp.core.dictionary.dao.DictionaryRepository;
import cn.bootx.bsp.core.dictionary.entity.Dictionary;
import cn.bootx.bsp.dto.dictionary.DictionaryDto;
import cn.bootx.bsp.exception.dictionary.ChildItemExistedException;
import cn.bootx.bsp.exception.dictionary.DictionaryAlreadyExistedException;
import cn.bootx.bsp.exception.dictionary.DictionaryNotExistedException;
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

	private final DictionaryRepository dictionaryRepository;
	private final DictionaryManager dictionaryManager;

	/**
	 * 添加字典
	 */
	@Transactional(rollbackFor = Exception.class)
	public DictionaryDto add(DictionaryDto dictionaryDto) {
		if (dictionaryManager.existsByName(dictionaryDto.getName())) {
			throw new DictionaryAlreadyExistedException();
		}
		Dictionary dictionary = Dictionary.init(dictionaryDto);
		return dictionaryRepository.save(dictionary).toDto();
	}

	/**
	 * 删除字典
	 */
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {

		if(dictionaryManager.existsById(id)) {
			throw new DictionaryNotExistedException();
		}
		// 存在子字典的场合不允许删除
		if (dictionaryManager.countByParentId(id) > 0) {
			throw new ChildItemExistedException();
		}
        dictionaryRepository.deleteById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public DictionaryDto update(DictionaryDto dictionaryDto) {

        Dictionary dictionary = dictionaryManager.findById(dictionaryDto.getId())
                .orElseThrow(DictionaryNotExistedException::new);

		// 判断字典是否重名
		if (dictionaryManager.existsByNameAndIdNot(dictionaryDto.getName(),dictionaryDto.getId())) {
			throw new DictionaryAlreadyExistedException();
		}

        BeanUtil.copyProperties(dictionaryDto,dictionary, CopyOptions.create().ignoreNullValue());
		return dictionaryRepository.save(dictionary).toDto();
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
}
