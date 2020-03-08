package com.gxlirong.tool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 *
 * </p>
 *
 * @author lirong
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ToolRbacUser对象", description = "用户表")
public class ToolRbacUser extends BaseUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户标识", dataType = "String")
    private Long id;

    @ApiModelProperty(value = "登录账号", dataType = "String")
    private String username;

    @ApiModelProperty(value = "用户密码", dataType = "String")
    private String password;

    @ApiModelProperty(value = "用户姓名", dataType = "String")
    private String realName;

    @ApiModelProperty(value = "手机号", dataType = "String")
    private String mobile;

    @ApiModelProperty(value = "邮箱", dataType = "String")
    private String email;

    @ApiModelProperty(value = "是否启用", dataType = "Boolean")
    private Boolean isEnabled;


}
