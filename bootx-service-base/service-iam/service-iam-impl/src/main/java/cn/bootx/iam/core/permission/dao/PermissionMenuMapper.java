package cn.bootx.iam.core.permission.dao;

import cn.bootx.iam.core.permission.entity.PermissionMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* 菜单权限
* @author xxm
* @date 2021/7/12
*/
@Mapper
public interface PermissionMenuMapper extends BaseMapper<PermissionMenu> {

}
