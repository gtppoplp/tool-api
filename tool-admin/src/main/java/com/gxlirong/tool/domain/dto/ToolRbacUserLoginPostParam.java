package com.gxlirong.tool.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录参数
 *
 * @author lirong
 */
@Data
public class ToolRbacUserLoginPostParam {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}
