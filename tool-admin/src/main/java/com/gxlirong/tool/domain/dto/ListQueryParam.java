package com.gxlirong.tool.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListQueryParam {
    @ApiModelProperty(value = "当前页数(默认1)")
    private Integer pageNum = 1;
    @ApiModelProperty(value = "显示数(100)")
    private Integer pageSize = 100;
}
