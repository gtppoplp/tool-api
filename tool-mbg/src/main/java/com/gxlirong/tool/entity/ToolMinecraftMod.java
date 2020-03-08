package com.gxlirong.tool.entity;

    import com.gxlirong.tool.entity.BaseUser;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 我的世界模组表
    * </p>
*
* @author lirong
* @since 2020-03-07
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @ApiModel(value="ToolMinecraftMod对象", description="我的世界模组表")
    public class ToolMinecraftMod extends BaseUser {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "模组标识")
    private Long id;

            @ApiModelProperty(value = "模组类型")
    private Long categoryId;

            @ApiModelProperty(value = "模组名称")
    private String name;

            @ApiModelProperty(value = "模型说明")
    private String description;

            @ApiModelProperty(value = "是否汉化")
    private Boolean isChinese;


}
