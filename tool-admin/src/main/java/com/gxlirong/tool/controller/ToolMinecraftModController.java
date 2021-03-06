package com.gxlirong.tool.controller;

import com.gxlirong.tool.common.api.CommonPage;
import com.gxlirong.tool.common.api.CommonResult;
import com.gxlirong.tool.domain.dto.ToolMinecraftModPostParam;
import com.gxlirong.tool.domain.dto.ToolMinecraftModQueryParam;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.service.ToolMinecraftModService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Api(tags = "游戏-我的世界-模组")
@RestController
@RequestMapping("/minecraft/mod")
public class ToolMinecraftModController {
    @Autowired
    private ToolMinecraftModService minecraftMod;

    @ApiOperation("列表")
    @GetMapping
    public CommonResult<CommonPage<ToolMinecraftMod>> list(ToolMinecraftModQueryParam minecraftModTypeQueryParam) {
        return CommonResult.success(CommonPage.restPage(minecraftMod.getList(minecraftModTypeQueryParam)));
    }

    @ApiOperation("新增")
    @PostMapping
    @Transactional
    public CommonResult<String> create(@RequestBody ToolMinecraftModPostParam minecraftModTypePostParam) {
        if (!minecraftMod.create(minecraftModTypePostParam)) {
            return CommonResult.failed("新增模组失败!");
        }
        return CommonResult.success("新增模组成功!");
    }

    @ApiOperation("通知读取lang")
    @PostMapping("/lang/{id}")
    @Transactional
    public CommonResult<String> lang(@PathVariable Long id) {
        if (!minecraftMod.lang(id)) {
            return CommonResult.failed("通知读取lang失败!");
        }
        return CommonResult.success("通知读取lang成功!");
    }

    @ApiOperation("通知汉化")
    @PostMapping("/chinese/{id}")
    @Transactional
    public CommonResult<String> chinese(@PathVariable Long id) {
        if (!minecraftMod.chinese(id)) {
            return CommonResult.failed("通知汉化失败!");
        }
        return CommonResult.success("通知汉化成功!");
    }

    @ApiOperation("编辑")
    @PutMapping("/{id}")
    @Transactional
    public CommonResult<String> update(@ApiParam(name = "id", value = "模组标识", required = true)
                                       @PathVariable Long id,
                                       @RequestBody ToolMinecraftModPostParam minecraftModTypePostParam) {
        if (!minecraftMod.update(id, minecraftModTypePostParam)) {
            return CommonResult.failed("修改模组失败!");
        }
        return CommonResult.success("修改模组成功!");
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    @Transactional
    public CommonResult<String> delete(@PathVariable Long id) {
        if (!minecraftMod.delete(id)) {
            return CommonResult.failed("删除模组失败!");
        }
        return CommonResult.success("删除模组成功!");
    }
}
