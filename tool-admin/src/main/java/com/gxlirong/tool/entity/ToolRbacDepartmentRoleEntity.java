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

    @TableName("tool_rbac_department_role")
    @ApiModel(value="ToolRbacDepartmentRoleEntity对象", description="")
    public class ToolRbacDepartmentRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "角色标识")
    private Long roleId;

            @ApiModelProperty(value = "部门标识")
    private Long departmentId;


}
