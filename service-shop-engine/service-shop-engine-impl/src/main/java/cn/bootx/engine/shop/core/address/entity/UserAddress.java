package cn.bootx.engine.shop.core.address.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.engine.shop.dto.address.UserAddressDto;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 用户收货地址
* @author xxm  
* @date 2021/1/31 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "se_user_address")
public class UserAddress extends JpaBaseEntity implements EntityBaseFunction<UserAddressDto> {

    /** 用户id */
    private Long userId;

    /** 标签tag */
    private String tag;

    /** 收货人 */
    private String consignee;

    /** 联系方式 */
    private String contact;

    /** 手机号 */
    private String phone;

    /** 邮政编码 */
    private String postalCode;

    /** 省编号 */
    private Integer provinceId;

    /** 省名称 */
    private String provinceName;

    /** 市编号 */
    private Integer cityId;

    /** 市名称 */
    private String cityName;

    /** 区县编号 */
    private Integer countyId;

    /** 区县名称 */
    private String countyName;

    /** 详细地址 */
    private String street;

    /** 是否默认地址 */
    @Column(name = "is_default")
    private Boolean isDefault;


    @Override
    public UserAddressDto toDto() {
        UserAddressDto dto = new UserAddressDto();
        BeanUtil.copyProperties(this,dto);
        return dto;
    }

    public static UserAddress init(UserAddressDto dto){
        UserAddress entity = new UserAddress();
        BeanUtil.copyProperties(dto,entity);
        return entity;
    }
}
