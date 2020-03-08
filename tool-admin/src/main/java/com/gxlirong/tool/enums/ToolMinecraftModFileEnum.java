package com.gxlirong.tool.enums;

import lombok.Getter;

@Getter
public enum ToolMinecraftModFileEnum {
    /**
     * 储存目录文件
     */
    CATEGORY_PERMANENT("file_permanent"),
    /**
     * 游戏目录文件
     */
    CATEGORY_GAME("file_game");

    /**
     * 文件类型
     */
    private String category;

    ToolMinecraftModFileEnum(String category) {
        this.category = category;
    }
}
