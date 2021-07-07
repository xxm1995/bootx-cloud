package cn.bootx.goodscenter.client.feign;

import cn.bootx.common.web.rest.ResResult;
import cn.bootx.goodscenter.code.GoodsCenterCode;
import cn.bootx.goodscenter.dto.category.CategoryAttrDefDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**   
* 类目属性定义
* @author xxm  
* @date 2021/3/21 
*/
@FeignClient(name = GoodsCenterCode.APPLICATION_NAME,contextId = "categoryAttrDefFeign",path = "/category")
interface CategoryAttrDefFeign {

    @ApiOperation("添加")
    @PostMapping("/add")
    ResResult<CategoryAttrDefDto> add(@RequestBody CategoryAttrDefDto dto);

    @ApiOperation("获取单条")
    @GetMapping("/get")
    ResResult<CategoryAttrDefDto> get(@RequestParam Long id);

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    ResResult<List<CategoryAttrDefDto>> findAll();
    
    @ApiOperation("按类目查询")
    @GetMapping("/findByCategory")
    ResResult<List<CategoryAttrDefDto>> findByCategory(@RequestParam Long cid);
}
