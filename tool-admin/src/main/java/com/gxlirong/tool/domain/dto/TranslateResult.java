package com.gxlirong.tool.domain.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TranslateResult {
    @ApiModelProperty("翻以前")
    private String src;
    @ApiModelProperty("翻译后")
    private String dst;
}