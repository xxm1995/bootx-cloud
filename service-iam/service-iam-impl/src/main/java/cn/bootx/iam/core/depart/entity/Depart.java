package cn.bootx.iam.core.depart.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.iam.dto.depart.DepartDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 部门表
* @author xxm  
* @date 2020/5/7 17:38 
*/
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name="uc_depart")
public class Depart extends JpaBaseEntity implements EntityBaseFunction<DepartDto> {

	/**父机构ID*/
	private Long parentId;
	/**机构/部门名称*/
	private String departName;
	/**英文名*/
	private String departNameEn;
	/**缩写*/
	private String departNameAbbr;
	/**排序*/
	private Integer departOrder;
	/**描述*/
	private String description;
	/**机构类别 1组织机构，2岗位*/
	private String orgCategory;
	/**机构类型*/
	private String orgType;
	/**机构编码*/
	private String orgCode;
	/**手机号*/
	private String mobile;
	/**传真*/
	private String fax;
	/**地址*/
	private String address;
	/**备注*/
	private String memo;
	/**状态（1启用，0不启用）*/
	private Integer status;

	public static Depart init(DepartDto departDto){
        Depart depart = new Depart();
        BeanUtil.copyProperties(departDto,depart);
        return depart;
    }

    @Override
    public DepartDto toDto() {
        DepartDto departDto = new DepartDto();
        BeanUtil.copyProperties(this,departDto);
        return departDto;
    }
}
