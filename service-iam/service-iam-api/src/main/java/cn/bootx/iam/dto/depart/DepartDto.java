package cn.bootx.iam.dto.depart;

import cn.bootx.common.core.rest.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/5/7 18:29
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("部门")
public class DepartDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -4511914397757014519L;

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("父机构ID")
    private Long parentId;

    @ApiModelProperty("机构/部门名称")
    private String departName;

    @ApiModelProperty("英文名")
    private String departNameEn;

    @ApiModelProperty("缩写")
    private String departNameAbbr;

    @ApiModelProperty("排序")
    private Integer departOrder;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("机构类别 1组织机构，2岗位")
    private String orgCategory;

    @ApiModelProperty("机构类型")
    private String orgType;

    @ApiModelProperty("机构编码")
    private String orgCode;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("传真")
    private String fax;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("备注")
    private String memo;

    @ApiModelProperty("状态（1启用，0不启用）")
    private String status;

}
