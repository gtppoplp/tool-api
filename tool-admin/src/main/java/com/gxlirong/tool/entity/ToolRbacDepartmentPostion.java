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
@ApiModel(value = "ToolRbacDepartmentPostion对象", description = "部门岗位表")
public class ToolRbacDepartmentPostion {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门标识", dataType = "String")
    private Long departmentId;
    @ApiModelProperty(value = "岗位标识", dataType = "String")
    private Long postionId;


}
