package com.gxlirong.tool.entity;

    import com.gxlirong.tool.entity.BaseUser;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 文件表
    * </p>
*
* @author lirong
* @since 2020-03-07
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @ApiModel(value="ToolFile对象", description="文件表")
    public class ToolFile extends BaseUser {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "文件标识")
    private Long id;

            @ApiModelProperty(value = "文件名(无扩展)")
    private String name;

            @ApiModelProperty(value = "文件扩展名")
    private String extension;

            @ApiModelProperty(value = "关联表标识")
    private Long tableId;

            @ApiModelProperty(value = "关联表名")
    private String tableName;

            @ApiModelProperty(value = "分类(用于区分文件组)")
    private String category;

            @ApiModelProperty(value = "文件路径(包含文件名)")
    private String path;


}
