package com.gxlirong.tool.entity;

    import com.gxlirong.tool.entity.BaseUser;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 我的世界模组
    * </p>
*
* @author lirong
* @since 2020-03-06
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @ApiModel(value="ToolMinecraftModCategory对象", description="我的世界模组")
    public class ToolMinecraftModCategory extends BaseUser {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "模组类型标识")
    private Long id;

            @ApiModelProperty(value = "模组类型名称")
    private String name;

            @ApiModelProperty(value = "模组类型说明")
    private String description;


}
