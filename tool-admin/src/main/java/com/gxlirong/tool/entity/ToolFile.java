package com.gxlirong.tool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author lirong
 * @since 2020-03-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ToolFile对象", description = "文件表")
public class ToolFile extends BaseUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文件标识", dataType = "String")
    private Long id;

    @ApiModelProperty(value = "文件名(无扩展,上传时文件名)", dataType = "String")
    private String name;

    @ApiModelProperty(value = "文件扩展名", dataType = "String")
    private String extension;

    @ApiModelProperty(value = "关联表标识", dataType = "String")
    private Long tableId;

    @ApiModelProperty(value = "关联表实体名", dataType = "String")
    private String entityName;

    @ApiModelProperty(value = "分类(用于区分文件组)", dataType = "String")
    private String category;

    @ApiModelProperty(value = "文件路径(包含文件名)", dataType = "String")
    private String path;


}
