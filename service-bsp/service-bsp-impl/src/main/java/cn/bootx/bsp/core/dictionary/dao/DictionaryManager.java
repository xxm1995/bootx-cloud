package cn.bootx.bsp.core.dictionary.dao;

import cn.bootx.bsp.core.dictionary.entity.Dictionary;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**   
* 字典
* @author xxm
* @date 2020/11/13 
*/
@Repository
@AllArgsConstructor
public class DictionaryManager{
    private final DictionaryRepository dictionaryRepository;
    private final HeaderHolder headerHolder;

    /**
     * 根据用户名查询重复
     */
    public boolean existsByName(String name){
        return dictionaryRepository.existsByNameAndTid(name,headerHolder.findTid());
    }

    /**
     * 根据用户名查询重复 排除id
     */
    public boolean existsByNameAndIdNot(String name,Long id){
        return dictionaryRepository.existsByNameAndIdNotAndTid(name,id,headerHolder.findTid());
    }

    /**
     * 查询子字典的个数
     */
    public int countByParentId(Long parentId){
        return dictionaryRepository.countByParentIdAndTid(parentId,headerHolder.findTid());
    }

    public boolean existsById(Long id) {
        return dictionaryRepository.existsByIdAndTid(id,headerHolder.findTid());
    }

    public Optional<Dictionary> findById(Long id) {
        return dictionaryRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public List<Dictionary> findAll() {
        return dictionaryRepository.findAllByTid(headerHolder.findTid());
    }
}
