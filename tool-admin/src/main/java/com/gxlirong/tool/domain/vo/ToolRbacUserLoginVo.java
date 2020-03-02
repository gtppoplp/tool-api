package com.gxlirong.tool.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录响应参数
 *
 * @author lirong
 */
@Data
public class ToolRbacUserLoginVo {
    @ApiModelProperty("token授权码")
    private String token;
    @ApiModelProperty("token响应头")
    private String tokenHead;
}
