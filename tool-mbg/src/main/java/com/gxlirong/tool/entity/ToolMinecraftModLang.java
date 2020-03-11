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
* @since 2020-03-11
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @ApiModel(value="ToolMinecraftModLang对象", description="我的世界模组表")
    public class ToolMinecraftModLang extends BaseUser {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "语言标识")
    private Long id;

            @ApiModelProperty(value = "模组序号")
    private Long modId;

            @ApiModelProperty(value = "字段")
    private String field;

            @ApiModelProperty(value = "翻译")
    private String lang;


}
