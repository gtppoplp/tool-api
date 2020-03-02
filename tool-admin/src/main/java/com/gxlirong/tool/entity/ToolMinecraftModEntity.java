package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("tool_minecraft_mod")
@ApiModel(description = "我的世界模组")
public class ToolMinecraftModEntity extends BaseUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "域空间(0运营方,1企业)")
    private Integer domain;

    @ApiModelProperty(value = "部门标识")
    private Long departmentId;

    @ApiModelProperty(value = "组织标识")
    private Long organizationId;

    @ApiModelProperty(value = "模组标识")
    private Long modId;

    @ApiModelProperty(value = "模组类型")
    private Long modType;

    @ApiModelProperty(value = "模组名称")
    private String modName;

    @ApiModelProperty(value = "模组说明")
    private String description;

    @ApiModelProperty(value = "是否删除")
    private Boolean isDeleted;


}
