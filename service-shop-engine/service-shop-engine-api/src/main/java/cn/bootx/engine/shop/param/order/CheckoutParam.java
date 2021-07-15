package cn.bootx.engine.shop.param.order;

import cn.bootx.engine.shop.dto.invoice.UserInvoiceDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
* @author xxm
* @date 2021/2/17
*/
@Data
@Accessors(chain = true)
@ApiModel("结算参数")
public class CheckoutParam {
    @ApiModelProperty("地址")
    private Long userAddressId;

    @ApiModelProperty("支付方式")
    private String payType;

    @ApiModelProperty("配送方式")
    private String delivery;

    @ApiModelProperty("优惠券")
    private List<Long> couponIds;

    @ApiModelProperty("留言")
    private String remark;

    @ApiModelProperty("发票信息")
    private UserInvoiceDto userInvoice;


}
