package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author lirong
 * @since 2020-02-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tool_rbac_user")
@ApiModel(value = "ToolRbacUserEntity对象", description = "")
public class ToolRbacUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "域空间(0运营方,1企业)")
    private Integer domain;

    @ApiModelProperty(value = "用户标识")
    private Long userId;

    @ApiModelProperty(value = "部门标识")
    private Long departmentId;

    @ApiModelProperty(value = "组织标识")
    private Long organizationId;

    @ApiModelProperty(value = "登录账号")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户姓名")
    private String realName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建人标识")
    private Long createdId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "修改人标识")
    private Long updatedId;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "删除人标识")
    private Long deletedId;

    @ApiModelProperty(value = "删除时间")
    private LocalDateTime deletedTime;

    @ApiModelProperty(value = "是否删除")
    private Boolean isDeleted;

    @ApiModelProperty(value = "是否启用")
    private Boolean isEnabled;


}
