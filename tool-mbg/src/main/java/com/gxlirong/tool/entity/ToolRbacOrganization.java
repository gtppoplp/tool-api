package com.gxlirong.tool.entity;

    import com.gxlirong.tool.entity.BaseUser;
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
* @since 2020-03-06
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @ApiModel(value="ToolRbacOrganization对象", description="")
    public class ToolRbacOrganization extends BaseUser {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "组织标识")
    private Long id;

            @ApiModelProperty(value = "组织名称")
    private String name;

            @ApiModelProperty(value = "组织说明")
    private String description;


}
