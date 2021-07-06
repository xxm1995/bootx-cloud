package cn.bootx.common.web.rest.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 单 id 参数
 * @author xxm
 */
@ApiModel("单 id 参数")
public class SingleIdParam implements Serializable {

    private static final long serialVersionUID = -9014016316890783082L;
    @ApiModelProperty(name = "id", value = "id", required = true)
    @NotNull(message = "id 不能为空")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
