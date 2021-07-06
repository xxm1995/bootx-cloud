package cn.bootx.bsp.core.dictionary.entity;

import cn.bootx.bsp.core.dictionary.convert.DictionaryConvert;
import cn.bootx.bsp.dto.dictionary.DictionaryDto;
import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.web.code.CommonCode;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 字典
* @author xxm  
* @date 2020/11/13 
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bsp_dictionary")
@SQLDelete(sql = "update bsp_dictionary set deleted=" + CommonCode.DELETE_FLAG + " where id=? and version=?")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class Dictionary extends JpaBaseEntity implements EntityBaseFunction<DictionaryDto> {

	//名称
	private String name;

	//描述
	private String description;

	//上级ID
	private Long parentId;

	public static Dictionary init(DictionaryDto dto){
		return DictionaryConvert.CONVERT.convert(dto);
	}

	@Override
	public DictionaryDto toDto(){
        return DictionaryConvert.CONVERT.convert(this);
    }
}
