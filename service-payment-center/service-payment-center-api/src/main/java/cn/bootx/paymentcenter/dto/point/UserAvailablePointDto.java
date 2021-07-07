package cn.bootx.paymentcenter.dto.point;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author cuibl
 * @date 2020-01-15
 */
@Data
@Accessors(chain = true)
@ApiModel("")
public class UserAvailablePointDto implements Serializable {
    private static final long serialVersionUID = -8902200475631288234L;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("积分数")
	private Integer points;

    public UserAvailablePointDto(Long userId, Long points) {
        this.userId = userId;
        this.points = Objects.isNull(points) ? 0 : points.intValue();
    }
}
