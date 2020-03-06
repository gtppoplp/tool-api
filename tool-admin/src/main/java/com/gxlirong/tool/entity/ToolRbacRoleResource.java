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
@ApiModel(value = "ToolRbacRoleResource对象", description = "角色资源表")
public class ToolRbacRoleResource {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色标识",dataType = "Long")
    private Long roleId;

    @ApiModelProperty(value = "资源标识",dataType = "Long")
    private Long resourceId;


}
