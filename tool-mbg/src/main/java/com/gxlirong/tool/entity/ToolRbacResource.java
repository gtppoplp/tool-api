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
    @ApiModel(value="ToolRbacResource对象", description="")
    public class ToolRbacResource extends BaseUser {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "资源标识")
    private Long id;

            @ApiModelProperty(value = "上级资源标识")
    private Long parentId;

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
