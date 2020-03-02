package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 我的世界模组
 * </p>
 *
 * @author lirong
 * @since 2020-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tool_minecraft_mod_type")
@ApiModel(description = "我的世界模组")
public class ToolMinecraftModTypeEntity extends BaseUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "模组类型标识")
    private Long typeId;

    @ApiModelProperty(value = "模组类型名称")
    private String typeName;

    @ApiModelProperty(value = "模组类型说明")
    private String description;
}
