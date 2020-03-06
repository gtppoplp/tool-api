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
    @ApiModel(value="ToolRbacUserPostion对象", description="")
    public class ToolRbacUserPostion extends BaseUser {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "用户标识")
    private Long userId;

            @ApiModelProperty(value = "岗位标识")
    private Long postionId;


}
