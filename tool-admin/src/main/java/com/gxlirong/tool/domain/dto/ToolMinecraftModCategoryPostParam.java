package com.gxlirong.tool.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 我的世界模组类型请求参数
 *
 * @author lirong
 */
@Data
public class ToolMinecraftModCategoryPostParam {
    @ApiModelProperty(value = "模组类型名称")
    private String name;

    @ApiModelProperty(value = "模组类型说明")
    private String description;
}
