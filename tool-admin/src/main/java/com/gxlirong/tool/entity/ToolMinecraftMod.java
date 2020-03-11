package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


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

    @ApiModelProperty(value = "是否汉化(1:汉化,2:汉化中,3:汉化失败,0:未汉化)", dataType = "Boolean")
    private Boolean isChinese;

    @ApiModelProperty(value = "是否应用到游戏(1:是,2:错误,3:否)", dataType = "Boolean")
    private Boolean isEnabled;
}
