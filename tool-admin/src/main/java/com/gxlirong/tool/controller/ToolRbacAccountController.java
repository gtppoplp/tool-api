package com.gxlirong.tool.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "安全管理-授权管理")
@RestController
@RequestMapping("/security/account")
public class ToolRbacAccountController {

    @ApiOperation("登录")
    @PostMapping("login")
    public String login() {
        return "hallo word";
    }
}
