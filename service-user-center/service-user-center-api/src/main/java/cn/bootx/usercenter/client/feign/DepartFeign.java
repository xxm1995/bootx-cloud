package cn.bootx.usercenter.client.feign;

import cn.bootx.common.web.rest.ResResult;
import cn.bootx.usercenter.code.UserCenterCode;
import cn.bootx.usercenter.dto.depart.DepartDto;
import cn.bootx.usercenter.dto.depart.DepartTreeResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = UserCenterCode.APPLICATION_NAME,contextId = "departFeign",path = "/depart")
public interface DepartFeign {

    @ApiOperation("添加")
    @PostMapping("/add")
    ResResult<DepartDto> add(@RequestBody DepartDto departDto);

    @ApiOperation("获取")
    @GetMapping("/get")
    ResResult<DepartDto> get(@RequestParam Long id);

    @ApiOperation("树状展示")
    @GetMapping("/tree")
    ResResult<List<DepartTreeResult>> tree();

    @ApiOperation("更新")
    @PostMapping("/update")
    ResResult<DepartDto> update(@RequestBody DepartDto departDto);

    @ApiOperation("普通删除")
    @DeleteMapping("/remove")
    ResResult<Void> remove(@RequestParam Long id);

    @ApiOperation("强制级联删除")
    @DeleteMapping("/removeAndChildren")
    ResResult<Boolean> removeAndChildren(@RequestParam Long id);
}
