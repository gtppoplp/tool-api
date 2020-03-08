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
@ApiModel(value = "ToolRbacUserRole对象", description = "用户角色表")
public class ToolRbacUserRole {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色标识", dataType = "String")
    private Long roleId;

    @ApiModelProperty(value = "用户标识", dataType = "String")
    private Long userId;


}
