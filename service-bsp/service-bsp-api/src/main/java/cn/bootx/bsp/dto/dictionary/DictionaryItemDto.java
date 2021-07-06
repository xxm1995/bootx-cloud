package cn.bootx.bsp.dto.dictionary;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* 数据字典项
* @author xxm
* @date 2020/4/15 17:55
*/
@Data
@ApiModel(value = "数据字典项Dto")
public class DictionaryItemDto implements Serializable {
    private static final long serialVersionUID = 7403674221398097611L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty(name = "dictionaryId", value = "字典ID", required = true)
    private Long dictionaryId;

    @ApiModelProperty(name = "name", value = "名称", required = true)
    private String name;

    @ApiModelProperty(name = "shortName", value = "简称")
    private String shortName;

    @ApiModelProperty(name = "value", value = "内容")
    private String value;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @ApiModelProperty(name = "parent_id", value = "上级字典项目ID")
    private Long parentId;

    @ApiModelProperty(name = "parent_name", value = "上级字典名称")
    private String parentName;

}
