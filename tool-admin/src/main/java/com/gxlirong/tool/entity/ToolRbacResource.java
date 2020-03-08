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
@ApiModel(value = "ToolRbacResource对象", description = "资源表")
public class ToolRbacResource extends BaseUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源标识", dataType = "String")
    private Long id;

    @ApiModelProperty(value = "上级资源标识", dataType = "String")
    private Long parentId;

    @ApiModelProperty(value = "路由", dataType = "String")
    private String url;

    @ApiModelProperty(value = "是否菜单项", dataType = "Boolean")
    private Boolean isMenu;

    @ApiModelProperty(value = "显示顺序号", dataType = "Integer")
    private Integer order;

    @ApiModelProperty(value = "资源代码", dataType = "String")
    private String sourceCode;

    @ApiModelProperty(value = "资源名称", dataType = "String")
    private String sourceName;

    @ApiModelProperty(value = "资源说明", dataType = "String")
    private String sourceExplain;


}
