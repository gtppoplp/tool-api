package com.gxlirong.tool.controller;

import com.gxlirong.tool.common.api.CommonPage;
import com.gxlirong.tool.common.api.CommonResult;
import com.gxlirong.tool.domain.dto.ToolMinecraftModTypePostParam;
import com.gxlirong.tool.domain.dto.ToolMinecraftModTypeQueryParam;
import com.gxlirong.tool.entity.ToolMinecraftModTypeEntity;
import com.gxlirong.tool.service.ToolMinecraftModTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Api(tags = "游戏-我的世界-模组类型")
@RestController
@RequestMapping("/minecraft/mod-type")
public class ToolMinecraftModTypeController {
    @Autowired
    private ToolMinecraftModTypeService minecraftModTypeService;

    @ApiOperation("列表")
    @GetMapping
    @ResponseBody
    public CommonResult<CommonPage<ToolMinecraftModTypeEntity>> list(ToolMinecraftModTypeQueryParam minecraftModTypeQueryParam) {
        return CommonResult.success(CommonPage.restPage(minecraftModTypeService.getList(minecraftModTypeQueryParam)));
    }

    @ApiOperation("新增")
    @PostMapping
    @ResponseBody
    @Transactional
    public CommonResult<String> create(@RequestBody ToolMinecraftModTypePostParam minecraftModTypePostParam) {
        if (!minecraftModTypeService.create(minecraftModTypePostParam)) {
            return CommonResult.failed("新增模组类型失败!");
        }
        return CommonResult.success("新增模组类型成功!");
    }

    @ApiOperation("编辑")
    @PutMapping("/{id}")
    @ResponseBody
    @Transactional
    public CommonResult<String> update(@ApiParam(name = "id", value = "模组类型标识", required = true)
                                       @PathVariable Long id,
                                       @RequestBody ToolMinecraftModTypePostParam minecraftModTypePostParam) {
        if (!minecraftModTypeService.update(id, minecraftModTypePostParam)) {
            return CommonResult.failed("修改模组类型失败!");
        }
        return CommonResult.success("修改模组类型成功!");
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    @ResponseBody
    @Transactional
    public CommonResult<String> delete(@PathVariable Long id) {
        if (!minecraftModTypeService.delete(id)) {
            return CommonResult.failed("删除模组类型失败!");
        }
        return CommonResult.success("删除模组类型成功!");
    }
}
