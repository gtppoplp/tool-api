package com.gxlirong.tool.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChineseTranslate {
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("错误代码")
    private Integer errorCode;
    @ApiModelProperty("经过实践")
    private Integer elapsedTime;
    @ApiModelProperty("翻译响应")
    private List<List<TranslateResult>> translateResult;

}


