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

    @ApiModelProperty(value = "模组名称")
    private String name;

    @ApiModelProperty(value = "模组类型")
    private String categoryId;

    @ApiModelProperty(value = "模组说明")
    private String description;

    @ApiModelProperty(value = "是否汉化")
    private Boolean isChinese = false;

    @ApiModelProperty(value = "文件路径")
    private String path;
}
