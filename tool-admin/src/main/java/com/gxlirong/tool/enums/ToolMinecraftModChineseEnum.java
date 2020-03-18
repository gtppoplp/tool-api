package com.gxlirong.tool.enums;

import lombok.Getter;

@Getter
public enum ToolMinecraftModChineseEnum {
    /**
     * 读取状态
     */
    CHINESE_STATUS(1, 2, 3, 0);

    /**
     * 完成
     */
    private Integer chineseComplete;
    /**
     * 提取中
     */
    private Integer chineseWorking;
    /**
     * 提取失败
     */
    private Integer chineseFail;
    /**
     * 未提取
     */
    private Integer chineseNot;

    ToolMinecraftModChineseEnum(Integer chineseComplete, Integer chineseWorking, Integer chineseFail, Integer chineseNot) {
        this.chineseComplete = chineseComplete;
        this.chineseWorking = chineseWorking;
        this.chineseFail = chineseFail;
        this.chineseNot = chineseNot;
    }
}
