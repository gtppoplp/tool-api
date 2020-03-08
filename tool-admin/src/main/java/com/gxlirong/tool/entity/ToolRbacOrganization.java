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
@ApiModel(value = "ToolRbacOrganization对象", description = "组织表")
public class ToolRbacOrganization extends BaseUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "组织标识", dataType = "String")
    private Long id;

    @ApiModelProperty(value = "组织名称", dataType = "String")
    private String name;

    @ApiModelProperty(value = "组织说明", dataType = "String")
    private String description;


}
