package cn.bootx.engine.shop.param.sell;

import cn.bootx.engine.shop.dto.invoice.UserInvoiceDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxm
* @date 2020/12/10
*/
@Data
@Accessors(chain = true)
@ApiModel("下单参数")
public class PlaceOrderParam implements Serializable {
    private static final long serialVersionUID = -857197106889234137L;

    /** 收货信息 */
    private Long userAddressId;

    /** 发票信息 */
    private UserInvoiceDto userInvoice;

    /** 物流方式 */
    private Long logisticsId;

    /** 优惠券 */
    private List<Long> couponIds;

}
