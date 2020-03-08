package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseUser {

    @ApiModelProperty(value = "域空间(0运营方,1企业)", dataType = "Integer")
    private Integer domain;

    @ApiModelProperty(value = "部门标识", dataType = "String")
    private Long departmentId;

    @ApiModelProperty(value = "组织标识", dataType = "String")
    private Long organizationId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建人标识", dataType = "String")
    private Long createdId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间", dataType = "LocalDateTime")
    private LocalDateTime createdTime;

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
