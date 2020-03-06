package com.gxlirong.tool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * <p>
 * 我的世界模组
 * </p>
 *
 * @author lirong
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ToolMinecraftMod对象", description = "我的世界模组表")
public class ToolMinecraftMod extends BaseUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模组标识", dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "模组类型", dataType = "Long")
    private Long categoryId;

    @ApiModelProperty(value = "模组名称", dataType = "String")
    private String name;

    @ApiModelProperty(value = "模组说明", dataType = "String")
    private String description;


}
