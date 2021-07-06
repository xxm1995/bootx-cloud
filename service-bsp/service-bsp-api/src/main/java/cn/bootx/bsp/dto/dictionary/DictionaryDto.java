package cn.bootx.bsp.dto.dictionary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/4/10 14:46
*/
@Data
@Accessors(chain = true)
@ApiModel("数据字典目录")
public class DictionaryDto implements Serializable {

	private static final long serialVersionUID = 8185789462442511856L;

	@ApiModelProperty("主键")
	private Long id;

	@ApiModelProperty("名称")
	private String name;

	@ApiModelProperty("描述")
	private String description;

	@ApiModelProperty("上级ID")
	private Long parentId;

	@ApiModelProperty("版本号")
	private Integer version;
}
