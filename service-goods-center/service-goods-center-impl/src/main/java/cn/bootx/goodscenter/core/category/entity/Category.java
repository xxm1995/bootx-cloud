package cn.bootx.goodscenter.core.category.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.web.code.CommonCode;
import cn.bootx.goodscenter.dto.category.CategoryDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
* 类目
* @author xxm
* @date 2020/11/19
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "gc_category")
@Accessors(chain = true)
@SQLDelete(sql = "update gc_category set deleted=" + CommonCode.DELETE_FLAG + " where id=? and version=? ")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class Category extends JpaBaseEntity implements EntityBaseFunction<CategoryDto> {

    private static final long serialVersionUID = -3604408346443111551L;

    /** 上级类目id */
    private Long pid;
    /** 类目名称 */
    private String name;
    /** 描述 */
    private String description;
    /** 序号 */
    private int ordinal;

    /** 是否叶节点 */
    @Column(name = "is_leaf")
    private boolean isLeaf;

    public static Category init(CategoryDto dto) {
        Category entity = new Category();
        BeanUtil.copyProperties(dto,entity);
        return entity;
    }

    @Override
    public CategoryDto toDto() {
        CategoryDto dto = new CategoryDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

}
