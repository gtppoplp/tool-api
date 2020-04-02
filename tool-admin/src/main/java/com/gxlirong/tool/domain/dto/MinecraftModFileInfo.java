package com.gxlirong.tool.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的世界mod配置文件信息
 *
 * @author lirong
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)//忽略多余的字段
public class MinecraftModFileInfo {
    @ApiModelProperty("包名")
    private String modid = "";
    @ApiModelProperty("模组名")
    private String name = "";
    @ApiModelProperty("描述")
    private String description = "";
    @ApiModelProperty("版本")
    private String version = "";
    @ApiModelProperty("我的世界版本")
    private String mcversion = "";
    @ApiModelProperty("url")
    private String url = "";
    @ApiModelProperty("作者列表")
    private String authorList = "";
    @ApiModelProperty("依赖")
    private String dependencies = "";
}
