package cn.bootx.goodscenter.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/11/19
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "类目DTO")
public class CategoryDto implements Serializable {
    private static final long serialVersionUID = -6395120064541083702L;

    /** 根类目 id */
    public static final long ID_ROOT = 0L;

    private Long id;

    @ApiModelProperty(name = "pid", value = "上级类目id")
    private Long pid;

    @ApiModelProperty(name = "name", value = "类目名称", required = true)
    private String name;

    @ApiModelProperty(name = "description", value = "类目描述")
    private String description;

    @ApiModelProperty(name = "ordinal", value = "序号，默认0")
    private int ordinal;

    @ApiModelProperty("是否叶子节点")
    private boolean isLeaf;

}