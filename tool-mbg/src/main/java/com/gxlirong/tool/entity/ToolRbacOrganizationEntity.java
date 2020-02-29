package com.gxlirong.tool.entity;

    import com.baomidou.mybatisplus.annotation.TableName;
    import java.time.LocalDateTime;
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

    @TableName("tool_rbac_organization")
    @ApiModel(value="ToolRbacOrganizationEntity对象", description="")
    public class ToolRbacOrganizationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "域空间(0运营方,1企业)")
    private Integer domain;

            @ApiModelProperty(value = "组织标识")
    private Long organizationId;

            @ApiModelProperty(value = "组织名称")
    private String organizationName;

            @ApiModelProperty(value = "组织说明")
    private String description;

            @ApiModelProperty(value = "创建人标识")
    private Long createdId;

            @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

            @ApiModelProperty(value = "修改人标识")
    private Long updatedId;

            @ApiModelProperty(value = "修改时间")
    private LocalDateTime updatedTime;

            @ApiModelProperty(value = "删除人标识")
    private Long deletedId;

            @ApiModelProperty(value = "删除时间")
    private LocalDateTime deletedTime;

            @ApiModelProperty(value = "是否删除")
    private Boolean isDeleted;


}
