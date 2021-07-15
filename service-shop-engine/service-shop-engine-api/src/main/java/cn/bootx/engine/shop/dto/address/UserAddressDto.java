package cn.bootx.engine.shop.dto.address;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**   
* @author xxm
* @date 2021/2/17 
*/
@Data
@Accessors(chain = true)
@ApiModel("用户地址")
public class UserAddressDto {
    private Long id;

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

    /** 省编号 */
    private Integer provinceId;

    /** 市编号 */
    private Integer cityId;

    /** 区编号 */
    private Integer countyId;

    /** 详细地址 */
    private String street;

    /** 是否默认地址 */
    private Boolean isDefault = Boolean.FALSE;

    /** 省名称 */
    private String provinceName;

    /** 市名称 */
    private String cityName;

    /** 区名称 */
    private String countyName;

    /** 邮政编码 */
    private String postalCode;
}
