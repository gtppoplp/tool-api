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
    @ApiModel(value="ToolRbacUser对象", description="")
    public class ToolRbacUser extends BaseUser {

    private static final long serialVersionUID = 1L;

            @ApiModelProperty(value = "用户标识")
    private Long id;

            @ApiModelProperty(value = "登录账号")
    private String username;

            @ApiModelProperty(value = "用户密码")
    private String password;

            @ApiModelProperty(value = "用户姓名")
    private String realName;

            @ApiModelProperty(value = "手机号")
    private String mobile;

            @ApiModelProperty(value = "邮箱")
    private String email;

            @ApiModelProperty(value = "是否启用")
    private Boolean isEnabled;


}
