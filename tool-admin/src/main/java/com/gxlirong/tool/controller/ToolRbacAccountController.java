package com.gxlirong.tool.controller;

import com.gxlirong.tool.common.api.CommonResult;
import com.gxlirong.tool.domain.dto.ToolRbacUserLoginPostParam;
import com.gxlirong.tool.domain.vo.ToolRbacUserInfoVo;
import com.gxlirong.tool.domain.vo.ToolRbacUserLoginVo;
import com.gxlirong.tool.entity.ToolRbacUserEntity;
import com.gxlirong.tool.service.ToolRbacUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Api(tags = "安全管理-授权管理")
@RestController
@RequestMapping("/security/account")
public class ToolRbacAccountController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private ToolRbacUserService rbacUserService;

    @ApiOperation("登录")
    @PostMapping("login")
    public CommonResult<ToolRbacUserLoginVo> login(@RequestBody ToolRbacUserLoginPostParam rbacUserLoginPostParam) {
        String token = rbacUserService.login(rbacUserLoginPostParam.getUsername(), rbacUserLoginPostParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        ToolRbacUserLoginVo rbacUserLoginVo = new ToolRbacUserLoginVo();
        rbacUserLoginVo.setToken(token);
        rbacUserLoginVo.setTokenHead(tokenHead);
        return CommonResult.success(rbacUserLoginVo);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/user/info")
    @ResponseBody
    public CommonResult<ToolRbacUserInfoVo> getUserInfo(Principal principal) {
        String username = principal.getName();
        ToolRbacUserEntity rbacUserEntity = rbacUserService.getUserByUsername(username);
        ToolRbacUserInfoVo toolRbacUserInfoVo = new ToolRbacUserInfoVo();
        toolRbacUserInfoVo.setUsername(rbacUserEntity.getUsername());
        toolRbacUserInfoVo.setRoleList(new String[]{"TEST"});
        toolRbacUserInfoVo.setIcon("");
        return CommonResult.success(toolRbacUserInfoVo);
    }


    @ApiOperation("登出")
    @PostMapping("/logout")
    @ResponseBody
    public CommonResult<String> logout() {
        return CommonResult.success(null);
    }
}
