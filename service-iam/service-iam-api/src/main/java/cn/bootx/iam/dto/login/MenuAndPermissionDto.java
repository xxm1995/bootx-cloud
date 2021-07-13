package cn.bootx.iam.dto.login;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel("用户菜单权限集合")
public class MenuAndPermissionDto {

    // 菜单
    private List<LoginMenuDto> menus;

    // 权限
    private List<LoginPermissionDto> permissions;
}
