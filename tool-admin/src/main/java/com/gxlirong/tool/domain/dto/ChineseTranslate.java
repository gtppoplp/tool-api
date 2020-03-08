package com.gxlirong.tool.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChineseTranslate {
    @ApiModelProperty("从")
    private String from;
    @ApiModelProperty("到")
    private String to;
    @ApiModelProperty("翻译响应")
    private List<TranslateResult> trans_result;

}


