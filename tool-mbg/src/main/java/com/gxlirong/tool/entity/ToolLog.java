package com.gxlirong.tool.entity;

    import com.gxlirong.tool.entity.BaseUser;
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 日志表
    * </p>
*
* @author lirong
* @since 2020-03-11
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @ApiModel(value="ToolLog对象", description="日志表")
    public class ToolLog extends BaseUser {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "日志标识")
    private Long id;

            @ApiModelProperty(value = "日志类型")
    private String type;

            @ApiModelProperty(value = "关联表标识")
    private Long tableId;

            @ApiModelProperty(value = "关联实体类名")
    private String entityName;

            @ApiModelProperty(value = "日志内容")
    private String log;


}
