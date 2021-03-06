package com.gxlirong.tool.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author lirong
 * @since 2020-03-12
 */
@Data
@ApiModel(value = "ToolMinecraftModInfo对象", description = "")
public class ToolMinecraftModInfo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模组信息序号")
    private Long id;

    @ApiModelProperty(value = "模组表标识")
    private Long minecraftModId;

    @ApiModelProperty(value = "包名")
    private String modid;

    @ApiModelProperty(value = "模组名")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "版本")
    private String version;

    @ApiModelProperty(value = "我的世界版本")
    private String mcversion;

    @ApiModelProperty(value = "url")
    private String url;

    @ApiModelProperty(value = "更新url")
    @TableField("updateUrl")
    private String updateUrl;

    @ApiModelProperty(value = "作者列表(json)")
    @TableField("authorList")
    private String authorList;

    @ApiModelProperty(value = "工作人员")
    private String credits;

    @ApiModelProperty(value = "logo附件")
    @TableField("logoFile")
    private String logoFile;

    @ApiModelProperty(value = "截图(json)")
    private String screenshots;

    @ApiModelProperty(value = "依赖(json)")
    private String dependencies;


}
