package cn.bootx.goodscenter.core.attributes.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.web.code.CommonCode;
import cn.bootx.goodscenter.dto.category.CategoryAttrDefDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
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
* 类目规格属性定义
* @author xxm
* @date 2020/11/21
*/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "gc_category_attr_def")
@SQLDelete(sql = "update gc_category_attr_def set deleted=" + CommonCode.DELETE_FLAG + " where id=? and version=? ")
@Where(clause = "deleted=" + CommonCode.NORMAL_FLAG)
public class CategoryAttrDef extends JpaBaseEntity implements EntityBaseFunction<CategoryAttrDefDto> {

    /** 所属类目id */
    private Long cid;
    /** 名称 */
    private String name;
    /**
     * 所属类型
     */
    private int type;
    /** 数据字典id */
    private Long dictId;
    /** 目标类型 sku/spu */
    private int targetType;

    /** 是否显示属性 */
    @Column(name = "is_display")
    private boolean display;
    /** 是否搜索属性 */
    @Column(name = "is_search")
    private boolean search;
    /** 是否必须 */
    @Column(name = "is_required")
    private boolean required;
    /** 是否多选 */
    @Column(name = "is_multiple")
    private boolean multiple;
    /** 排序 */
    private int ordinal;
    /** 状态 */
    private int state;
    /** 属性名称 */
    private String fieldName;
    /** 属性长度 */
    private Integer fieldLength;
    /** 小数点长度 */
    private Integer fieldPointLength;
    /** 属性类型 */
    private String fieldType;
    /** 字段默认值 */
    private String fieldDefault;
    /** 字段查询时的比较方式 */
    private String queryCompareType;
    /** 是否主键 */
    private Boolean isKey;
    /** 描述 */
    private String description;

    public static CategoryAttrDef init(CategoryAttrDefDto dto) {
        CategoryAttrDef entity = new CategoryAttrDef();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public CategoryAttrDefDto toDto() {
        CategoryAttrDefDto dto = new CategoryAttrDefDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
