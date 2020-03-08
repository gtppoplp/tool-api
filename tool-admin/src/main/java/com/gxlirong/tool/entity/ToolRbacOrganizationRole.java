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
@ApiModel(value = "ToolRbacOrganizationRole对象", description = "组织角色表")
public class ToolRbacOrganizationRole{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "组织标识", dataType = "String")
    private Long organizationId;
    @ApiModelProperty(value = "角色标识",dataType = "String")
    private Long roleId;


}
