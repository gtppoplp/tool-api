package com.gxlirong.tool.enums;

import lombok.Getter;

@Getter
public enum LogEnum {

    /**
     * 日志类型 - 错误
     */
    LOG_TYPE_ERROR("error");
    /**
     * 日志类型
     */
    private String type;

    LogEnum(String type) {
        this.type = type;
    }
}
