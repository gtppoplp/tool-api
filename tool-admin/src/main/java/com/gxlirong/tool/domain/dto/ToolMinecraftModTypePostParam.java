package com.gxlirong.tool.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 我的世界模组类型请求参数
 *
 * @author lirong
 */
@Data
public class ToolMinecraftModTypePostParam {
    @ApiModelProperty(value = "模组类型名称")
    private String typeName;

    @ApiModelProperty(value = "模组类型说明")
    private String description;
}
