package com.gxlirong.tool.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 我的世界模组查询参数
 *
 * @author lirong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ToolMinecraftModQueryParam extends ListQueryParam {
    @ApiModelProperty(value = "模型名称")
    private String name;
}
