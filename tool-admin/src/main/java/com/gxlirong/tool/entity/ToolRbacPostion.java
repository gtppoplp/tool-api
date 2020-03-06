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
@ApiModel(value = "ToolRbacPostion对象", description = "岗位表")
public class ToolRbacPostion extends BaseUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "岗位标识",dataType = "Long")
    private Long id;

    @ApiModelProperty(value = "岗位名称",dataType = "String")
    private String name;

    @ApiModelProperty(value = "岗位说明",dataType = "String")
    private String description;


}
