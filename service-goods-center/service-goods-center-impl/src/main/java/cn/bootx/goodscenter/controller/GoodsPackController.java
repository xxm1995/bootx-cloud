package cn.bootx.goodscenter.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xxm
* @date 2020/11/23
*/
@Api(tags = "商品打包")
@RestController
@RequestMapping("/pack/goods")
@AllArgsConstructor
public class GoodsPackController {
}
