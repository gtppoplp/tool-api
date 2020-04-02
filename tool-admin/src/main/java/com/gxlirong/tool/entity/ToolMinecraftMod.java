package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * <p>
 * 我的世界模组
 * </p>
 *
 * @author lirong
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ToolMinecraftMod对象", description = "我的世界模组表")
public class ToolMinecraftMod extends BaseUser {

    private static final long serialVersionUID = 1L;
    @TableId
    @ApiModelProperty(value = "模组标识", dataType = "String")
    private Long id;

    @ApiModelProperty(value = "模组类型", dataType = "String")
    private Long categoryId;

    @ApiModelProperty(value = "模组名称", dataType = "String")
    private String name;

    @ApiModelProperty(value = "模组说明", dataType = "String")
    private String description;

    @ApiModelProperty(value = "读取lang状态(1:完成,2:提取中,3:提取失败,0:未提取)", dataType = "Integer")
    private Integer langStatus;

    @ApiModelProperty(value = "汉化状态(1:已汉化,2:汉化中,3:汉化失败,0:未汉化)", dataType = "Integer")
    private Integer chineseStatus;

    @ApiModelProperty(value = "应用到游戏状态(1:已应用,2:应用中,3:应用失败,0:未应用)", dataType = "Integer")
    private Integer enabledStatus;

    @ApiModelProperty(value = "无法汉化(0:否,1:是)", dataType = "Boolean")
    private Boolean unableChinese;
}
