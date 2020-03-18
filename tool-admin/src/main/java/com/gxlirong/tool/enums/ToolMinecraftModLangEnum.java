package com.gxlirong.tool.enums;

import lombok.Getter;

@Getter
public enum ToolMinecraftModLangEnum {
    /**
     * 读取状态
     */
    LANG_STATUS(1, 2, 3, 0);

    /**
     * 完成
     */
    private Integer langComplete;
    /**
     * 提取中
     */
    private Integer langWorking;
    /**
     * 提取失败
     */
    private Integer langFail;
    /**
     * 未提取
     */
    private Integer langNot;

    ToolMinecraftModLangEnum(Integer langComplete, Integer langWorking, Integer langFail, Integer langNot) {
        this.langComplete = langComplete;
        this.langWorking = langWorking;
        this.langFail = langFail;
        this.langNot = langNot;
    }
}
