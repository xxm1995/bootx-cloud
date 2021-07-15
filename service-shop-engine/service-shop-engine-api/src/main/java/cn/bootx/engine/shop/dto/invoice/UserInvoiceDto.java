package cn.bootx.engine.shop.dto.invoice;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**   
* @author xxm
* @date 2021/2/17 
*/
@Data
@Accessors(chain = true)
@ApiModel("发票信息")
public class UserInvoiceDto implements Serializable {
    private static final long serialVersionUID = 6750197650424355571L;

    private Long id;

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
}
