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

@TableName("tool_rbac_resource")
@ApiModel(value = "ToolRbacResourceEntity对象", description = "")
public class ToolRbacResourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "域空间(0运营方,1企业)")
    private Integer domain;

    @ApiModelProperty(value = "资源标识")
    private Long resourceId;

    @ApiModelProperty(value = "上级资源标识")
    private Long parentResourceId;

    @ApiModelProperty(value = "路由")
    private String url;

    @ApiModelProperty(value = "是否菜单项")
    private Boolean isMenu;

    @ApiModelProperty(value = "显示顺序号")
    private Integer order;

    @ApiModelProperty(value = "资源代码")
    private String sourceCode;

    @ApiModelProperty(value = "资源名称")
    private String sourceName;

    @ApiModelProperty(value = "资源说明")
    private String sourceExplain;


}
