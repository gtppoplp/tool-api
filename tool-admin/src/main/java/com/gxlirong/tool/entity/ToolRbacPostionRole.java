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
@ApiModel(value = "ToolRbacPostionRole对象", description = "岗位角色表")
public class ToolRbacPostionRole {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色标识", dataType = "String")
    private Long roleId;

    @ApiModelProperty(value = "岗位标识", dataType = "String")
    private Long postionId;


}
