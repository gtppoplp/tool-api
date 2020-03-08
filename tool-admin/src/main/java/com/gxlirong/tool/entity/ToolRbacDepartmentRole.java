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
@ApiModel(value = "ToolRbacDepartmentRole对象", description = "部门角色表")
public class ToolRbacDepartmentRole {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门标识", dataType = "String")
    private Long departmentId;

    @ApiModelProperty(value = "角色标识", dataType = "String")
    private Long roleId;


}
