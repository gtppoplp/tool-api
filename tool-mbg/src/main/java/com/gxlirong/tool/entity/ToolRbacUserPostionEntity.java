package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author lirong
 * @since 2020-02-29
 */
@Data
@EqualsAndHashCode(callSuper = false)

@TableName("tool_rbac_user_postion")
@ApiModel(value = "ToolRbacUserPostionEntity对象", description = "")
public class ToolRbacUserPostionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户标识")
    private Long userId;

    @ApiModelProperty(value = "岗位标识")
    private Long postionId;


}
