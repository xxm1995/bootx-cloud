package cn.bootx.bsp.core.dictionary.service;

import cn.bootx.bsp.core.dictionary.dao.DictionaryItemManager;
import cn.bootx.bsp.core.dictionary.dao.DictionaryItemRepository;
import cn.bootx.bsp.core.dictionary.entity.DictionaryItem;
import cn.bootx.bsp.dto.dictionary.DictionaryItemDto;
import cn.bootx.bsp.exception.dictionary.DictionaryItemAlreadyExistedException;
import cn.bootx.bsp.exception.dictionary.DictionaryItemNotExistedException;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.starter.jpa.utils.JpaUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xxm
 * @date 2020/4/16 21:16
 */
@Service
@AllArgsConstructor
public class DictionaryItemService {
	private final DictionaryItemRepository dictionaryItemRepository;
	private final DictionaryItemManager dictionaryItemManager;

	/**
	 * 添加内容
	 */
	public DictionaryItemDto add(DictionaryItemDto dictionaryItemDto){
		// 在同一个Dictionary不允许存在相同名字的DictionaryItem
		if (dictionaryItemManager.existsByName(dictionaryItemDto.getName(), dictionaryItemDto.getDictionaryId())) {
			throw new DictionaryItemAlreadyExistedException();
		}

		DictionaryItem dictionaryItem = DictionaryItem.init(dictionaryItemDto);
        dictionaryItem =dictionaryItemRepository.save(dictionaryItem);
		return dictionaryItem.toDto();
	}

	/**
	 * 删除内容
	 */
	public void delete(Long id) {
        dictionaryItemRepository.deleteById(id);
	}
	/**
	 * 修改内容
	 */
	@Transactional(rollbackFor = Exception.class)
	public DictionaryItemDto update(DictionaryItemDto dictionaryItemDto) {

        // 判断字典item是否存在
        DictionaryItem dictionaryItem = dictionaryItemManager.findById(dictionaryItemDto.getId())
                .orElseThrow(DictionaryItemNotExistedException::new);

        // 判断是否有重名的Item
		if (dictionaryItemManager.existsByName(dictionaryItemDto.getName(),dictionaryItemDto.getDictionaryId(),dictionaryItemDto.getId())) {
			throw new DictionaryItemAlreadyExistedException();
		}
        BeanUtil.copyProperties(dictionaryItemDto,dictionaryItem, CopyOptions.create().ignoreNullValue());
		return dictionaryItemRepository.save(dictionaryItem).toDto();
	}

	/**
	 * 根据ID查询指定内容
	 */
	public DictionaryItemDto findById(Long id) {
		return dictionaryItemManager.findById(id).map(DictionaryItem::toDto).orElse(null);
	}

	/**
	 * 查询指定目录下的所有内容
	 */
	public List<DictionaryItemDto> findByDictionaryId(Long dictionaryId) {
		return dictionaryItemManager.findByDictionaryId(dictionaryId)
                .stream()
				.map(DictionaryItem::toDto)
				.collect(Collectors.toList());
	}

	/**
	 * 分页查询指定目录下的内容
	 */
	public PageResult<DictionaryItemDto> pageByDictionaryId(Long dictionaryId, PageParam pageParam) {
        Page<DictionaryItem> dictionaryItems = dictionaryItemManager.findAllByDictionaryId(dictionaryId, pageParam);
		return JpaUtils.convert2PageResult(dictionaryItems);

	}


	/**
	 * 模糊查询
	 */
	public List<DictionaryItemDto> search(Long dictionaryId, String word) {
		return dictionaryItemManager.search(dictionaryId, word)
                .stream()
                .map(DictionaryItem::toDto)
                .collect(Collectors.toList());
	}

	/**
	 * 模糊查询
	 */
	public PageResult<DictionaryItemDto> search(Long dictionaryId, String word, PageParam pageParam) {
		return JpaUtils.convert2PageResult(dictionaryItemManager.search(dictionaryId, word, pageParam));
	}


	/**
	 * 根据ID集合获取相应的item对象
	 */
	public Map<Long, DictionaryItem> getIdItemMap(List<Long> dictionaryItemId) {
		return dictionaryItemManager.listByIds(dictionaryItemId)
                .stream()
                .collect(Collectors.toMap(DictionaryItem::getId, dictionaryItem -> dictionaryItem));
	}
}
