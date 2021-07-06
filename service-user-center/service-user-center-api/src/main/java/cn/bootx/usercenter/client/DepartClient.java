package cn.bootx.usercenter.client;

import cn.bootx.usercenter.dto.depart.DepartDto;
import cn.bootx.usercenter.dto.depart.DepartTreeResult;

import java.util.List;

/**
* 部门
* @author xxm
* @date 2020/6/2 15:27
*/
public interface DepartClient {
    /**
     * 添加部门
     */
    DepartDto add(DepartDto departDto);

    /**
     * 树状展示
     */
    List<DepartTreeResult> tree();

    /**
     * 根据id查询
     */
    DepartDto getById(Long id);

    /**
     * 编辑数据 编辑部门的部分数据,并保存到数据库
     */
    DepartDto edit(DepartDto departDto);

    /**
     * 删除部门
     */
    void deleteById(Long id);

    /**
     * 删除部门及下级部门
     */
    boolean removeAndChildren(Long id);
}
