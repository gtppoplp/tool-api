package com.gxlirong.tool.enums;

import lombok.Getter;

@Getter
public enum ToolMinecraftModEnabledEnum {
    /**
     * 读取状态
     */
    Enabled_STATUS(1, 2, 3, 0);

    /**
     * 完成
     */
    private Integer enabledComplete;
    /**
     * 提取中
     */
    private Integer enabledWorking;
    /**
     * 提取失败
     */
    private Integer enabledFail;
    /**
     * 未提取
     */
    private Integer enabledNot;

    ToolMinecraftModEnabledEnum(Integer enabledComplete, Integer enabledWorking, Integer enabledFail, Integer enabledNot) {
        this.enabledComplete = enabledComplete;
        this.enabledWorking = enabledWorking;
        this.enabledFail = enabledFail;
        this.enabledNot = enabledNot;
    }
}
