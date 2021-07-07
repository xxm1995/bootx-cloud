package cn.bootx.goodscenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.goodscenter.core.attributes.service.CategoryAttrDefService;
import cn.bootx.goodscenter.dto.category.CategoryAttrDefDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "类目定义")
@RestController
@RequestMapping("/category/attrDef")
@AllArgsConstructor
public class CategoryAttrDefController {
    private final CategoryAttrDefService categoryAttrDefService;

    @ApiOperation("添加")
    @PostMapping("/add")
    public ResResult<CategoryAttrDefDto> add(@RequestBody CategoryAttrDefDto dto){
        return Res.ok(categoryAttrDefService.addAttrDef(dto));
    }

    @ApiOperation("获取单条")
    @GetMapping("/get")
    public ResResult<CategoryAttrDefDto> get(Long id){
        return Res.ok(categoryAttrDefService.getById(id));
    }

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    public ResResult<List<CategoryAttrDefDto>> findAll(){
        return Res.ok(categoryAttrDefService.findAll());
    }

    @ApiOperation("按类目查询")
    @GetMapping("/findByCategory")
    public ResResult<List<CategoryAttrDefDto>> findByCategory(Long cid){
        return Res.ok(categoryAttrDefService.findByCategory(cid));
    }
}
