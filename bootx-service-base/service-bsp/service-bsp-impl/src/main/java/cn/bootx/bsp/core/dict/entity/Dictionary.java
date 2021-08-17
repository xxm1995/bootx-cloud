package cn.bootx.bsp.core.dict.entity;

import cn.bootx.bsp.core.dict.convert.DictionaryConvert;
import cn.bootx.bsp.dto.dict.DictionaryDto;
import cn.bootx.bsp.param.dict.DictionaryParam;
import cn.bootx.common.core.function.EntityBaseFunction;
import cn.bootx.common.mybatisplus.base.MpBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**   
* 字典
* @author xxm  
* @date 2020/11/13 
*/
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_dict")
public class Dictionary extends MpBaseEntity implements EntityBaseFunction<DictionaryDto> {

	/** 名称 */
	private String name;

	/** 编码 */
	private String code;

	/** 备注 */
	private String remark;

	public static Dictionary init(DictionaryDto in){
		return DictionaryConvert.CONVERT.convert(in);
	}

	public static Dictionary init(DictionaryParam in){
		return DictionaryConvert.CONVERT.convert(in);
	}

	@Override
	public DictionaryDto toDto(){
        return DictionaryConvert.CONVERT.convert(this);
    }
}
