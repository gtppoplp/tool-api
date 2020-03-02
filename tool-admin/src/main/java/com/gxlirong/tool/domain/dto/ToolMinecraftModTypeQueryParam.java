package com.gxlirong.tool.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 我的世界模组类型查询参数
 *
 * @author lirong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ToolMinecraftModTypeQueryParam extends ListQueryParam {
    private String typeName;
}
