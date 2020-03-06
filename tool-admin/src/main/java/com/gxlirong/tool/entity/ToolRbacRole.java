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
@ApiModel(value = "ToolRbacRole对象", description = "角色表")
public class ToolRbacRole extends BaseUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色标识",dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "角色名称",dataType = "String")
    private String name;

    @ApiModelProperty(value = "角色描述",dataType = "String")
    private String description;


}
