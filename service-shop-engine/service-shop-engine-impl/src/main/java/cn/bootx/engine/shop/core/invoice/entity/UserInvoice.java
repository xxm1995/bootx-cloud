package cn.bootx.engine.shop.core.invoice.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.engine.shop.dto.invoice.UserInvoiceDto;
import cn.bootx.common.jpa.base.JpaBaseEntity;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**   
* 用户发票信息
* @author xxm  
* @date 2021/1/31 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Entity
@Table(name = "se_invoice")
public class UserInvoice extends JpaBaseEntity implements EntityBaseFunction<UserInvoiceDto> {

    /** 所属用户 */
    private Long userId;

    /** 发票类型,0:个人,1:企业 ,2:增值税 */
    private Integer invoiceType;

    /** 发票抬头 */
    private String title;

    /** 税号 */
    private String contact;

     /** 注册地址 */
    private String registerAddress;

    /** 注册电话 */
    private String registerMobile;

    /** 开户银行 */
    private String bankName;

    /** 银行账户 */
    private String bankNo;

    @Override
    public UserInvoiceDto toDto() {
        UserInvoiceDto dto = new UserInvoiceDto();
        BeanUtil.copyProperties(this,dto);
        return dto;
    }

    public static UserInvoice init(UserInvoiceDto dto){
        UserInvoice entity = new UserInvoice();
        BeanUtil.copyProperties(dto,entity);
        return entity;
    }
}
