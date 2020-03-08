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
@ApiModel(value = "ToolRbacUserPostion对象", description = "用户岗位表")
public class ToolRbacUserPostion{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户标识",dataType = "String")
    private Long userId;

    @ApiModelProperty(value = "岗位标识",dataType = "String")
    private Long postionId;


}
