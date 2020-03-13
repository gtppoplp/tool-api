package com.gxlirong.tool.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 我的世界mod配置文件信息
 *
 * @author lirong
 */
@Data
public class MinecraftModFileInfo {
    @ApiModelProperty("包名")
    private String modid;
    @ApiModelProperty("模组名")
    private String name;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("版本")
    private String version;
    @ApiModelProperty("我的世界版本")
    private String mcversion;
    @ApiModelProperty("url")
    private String url;
    @ApiModelProperty("更新url")
    private String updateUrl;
    @ApiModelProperty("作者列表")
    private List<String> authorList;
    @ApiModelProperty("工作人员")
    private String credits;
    @ApiModelProperty("logo附件")
    private String logoFile;
    @ApiModelProperty("截图")
    private List<String> screenshots;
    @ApiModelProperty("依赖")
    private List<String> dependencies;
}
