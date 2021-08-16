package cn.bootx.paymentcenter.dto.point;

import cn.bootx.common.core.rest.dto.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* 积分日志
* @author xxm
* @date 2020/12/11
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("积分日志")
public class PointLogDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 910307468055915058L;
    private Long userId;

    @ApiModelProperty("积分数量")
    private int points;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("积分来源")
    private String source;

    @ApiModelProperty("积分类型名称等")
    private String typeName;

    @ApiModelProperty("用户信息ID")
    private Long uid;

    @ApiModelProperty("用户名")
    private String userName;

}
