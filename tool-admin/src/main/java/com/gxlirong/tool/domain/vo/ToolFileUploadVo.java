package com.gxlirong.tool.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文件上传响应参数
 *
 * @author lirong
 */
@Data
public class ToolFileUploadVo {
    @ApiModelProperty("附件名")
    private String path;
}
