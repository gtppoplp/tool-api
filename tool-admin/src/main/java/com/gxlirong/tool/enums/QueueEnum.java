package com.gxlirong.tool.enums;

import lombok.Getter;

@Getter
public enum QueueEnum {

    /**
     * 我的世界 消息通知队列->通知汉化
     */
    QUEUE_MINECRAFT_CHINESE("mod.minecraft.direct", "mod.minecraft.chinese", "mod.minecraft.chinese"),

    /**
     * 我的世界 消息通知队列->通知保存对应字段信息
     */
    QUEUE_MINECRAFT_LANG_CREATE("mod.minecraft.direct", "mod.minecraft.lang_create", "mod.minecraft.lang_create");
    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
