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
    @ApiModelProperty(value = "模组类型", dataType = "String")
    private Long categoryId;
    @ApiModelProperty(value = "读取lang状态(1:完成,2:提取中,3:提取失败,0:未提取)", dataType = "Integer")
    private Integer langStatus;
    @ApiModelProperty(value = "汉化状态(1:已汉化,2:汉化中,3:汉化失败,0:未汉化)", dataType = "Integer")
    private Integer chineseStatus;
    @ApiModelProperty(value = "应用到游戏状态(1:已应用,2:应用中,3:应用失败,0:未应用)", dataType = "Integer")
    private Integer enabledStatus;
}
