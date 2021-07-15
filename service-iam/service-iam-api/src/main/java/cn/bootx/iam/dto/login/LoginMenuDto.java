package cn.bootx.iam.dto.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
*
* @author xxm
* @date 2021/7/12
*/
@Data
@Accessors(chain = true)
@ApiModel("菜单路由")
public class LoginMenuDto {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long parentId;

    @JsonIgnore
    private Double sortNo;

    /** 路径 */
    private String path;

    /** 路由name */
    private String name;

    /** 组件 */
    private String component;

    /** 元信息 */
    private LoginMenuMetaDto mate;

    /** 子菜单 */
    private List<LoginMenuDto> children;
}
