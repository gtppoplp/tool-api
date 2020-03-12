package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 我的世界模组表
 * </p>
 *
 * @author lirong
 * @since 2020-03-11
 */
@Data
@ApiModel(value = "ToolMinecraftModLang对象", description = "我的世界模组表")
public class ToolMinecraftModLang {

    private static final long serialVersionUID = 1L;
    @TableId
    @ApiModelProperty(value = "语言标识")
    private Long id;

    @ApiModelProperty(value = "模组序号")
    private Long modId;

    @ApiModelProperty(value = "字段")
    private String field;

    @ApiModelProperty(value = "英文")
    private String enLang;

    @ApiModelProperty(value = "翻译")
    private String lang;

    @ApiModelProperty(value = "是否汉化(1:汉化,0:未汉化)", dataType = "Boolean")
    private Boolean isChinese;
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新人标识", dataType = "String")
    private Long updatedId;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "更新时间", dataType = "LocalDateTime")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "删除人标识", dataType = "String")
    private Long deletedId;

    @ApiModelProperty(value = "删除时间", dataType = "LocalDateTime")
    private LocalDateTime deletedTime;

    @TableField(strategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "是否删除", dataType = "Boolean")
    private Boolean isDeleted = false;
}
