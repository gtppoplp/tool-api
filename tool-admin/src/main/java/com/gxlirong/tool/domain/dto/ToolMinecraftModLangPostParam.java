package com.gxlirong.tool.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ToolMinecraftModLangPostParam {
    @ApiModelProperty(value = "翻译")
    private String lang;
}
