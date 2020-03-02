package com.gxlirong.tool.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户信息响应参数
 *
 * @author lirong
 */
@Data
public class ToolRbacUserInfoVo {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("角色列表")
    private String[] roleList;
    @ApiModelProperty("图标")
    private String icon;
}
