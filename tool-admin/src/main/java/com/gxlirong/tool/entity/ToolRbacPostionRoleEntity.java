package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@TableName("tool_rbac_postion_role")
@ApiModel(value = "ToolRbacPostionRoleEntity对象", description = "")
public class ToolRbacPostionRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色标识")
    private Long roleId;

    @ApiModelProperty(value = "岗位标识")
    private Long postionId;


}
