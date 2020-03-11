package com.gxlirong.tool.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 我的世界模组请求参数
 *
 * @author lirong
 */
@Data
public class ToolMinecraftModPostParam {

    @ApiModelProperty(value = "模组名称", dataType = "String")
    private String name;

    @ApiModelProperty(value = "模组类型", dataType = "String")
    private String categoryId;

    @ApiModelProperty(value = "模组说明", dataType = "String")
    private String description;

    @ApiModelProperty(value = "是否应用到游戏", dataType = "Boolean")
    private Boolean isEnabled = false;
    @ApiModelProperty(value = "文件路径", dataType = "String")
    private String path;
    @ApiModelProperty(value = "真实文件名", dataType = "String")
    private String fileName;
}
