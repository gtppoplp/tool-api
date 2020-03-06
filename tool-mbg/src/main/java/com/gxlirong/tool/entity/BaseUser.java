package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseUser {

    @ApiModelProperty(value = "域空间(0运营方,1企业)")
    private Integer domain;

    @ApiModelProperty(value = "部门标识")
    private Long departmentId;

    @ApiModelProperty(value = "组织标识")
    private Long organizationId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建人标识")
    private Long createdId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty("更新人标识")
    private Long updatedId;

    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty("删除人标识")
    private Long deletedId;

    @ApiModelProperty("删除时间")
    private LocalDateTime deletedTime;

    @TableField(strategy = FieldStrategy.IGNORED)
    @ApiModelProperty(value = "是否删除")
    private Boolean isDeleted = false;
}
