package cn.bootx.bsp.core.dictionary.entity;

import cn.bootx.bsp.core.dictionary.convert.DictionaryConvert;
import cn.bootx.bsp.dto.dictionary.DictionaryItemDto;
import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 字典项
 * @author xxm
 * @date 2020/4/15 17:45
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bsp_dictionary_item")
@Data
@Accessors(chain = true)
public class DictionaryItem extends JpaBaseEntity implements EntityBaseFunction<DictionaryItemDto> {

    //目录ID
    private Long dictionaryId;

    //名称
    private String name;

    //简称
    private String shortName;

    //内容
    private String value;

    //备注
    private String remark;

    //上级ID
    private Long parentId;

    public static DictionaryItem init(DictionaryItemDto dto) {
        return DictionaryConvert.CONVERT.convert(dto);
    }

    @Override
    public DictionaryItemDto toDto() {
        return DictionaryConvert.CONVERT.convert(this);
    }

}

