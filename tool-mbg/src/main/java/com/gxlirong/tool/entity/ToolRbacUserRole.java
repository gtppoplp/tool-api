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
    @ApiModel(value="ToolRbacUserRole对象", description="")
    public class ToolRbacUserRole extends BaseUser {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "角色标识")
    private Long roleId;

            @ApiModelProperty(value = "用户标识")
    private Long userId;


}
