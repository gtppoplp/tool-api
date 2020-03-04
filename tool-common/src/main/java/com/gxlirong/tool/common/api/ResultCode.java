package com.gxlirong.tool.common.api;

import lombok.Getter;

/**
 * 枚举了一些常用API操作码
 *
 * @author lirong
 */
@Getter
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    //我的世界-模型类型
    MINECRAFT_MOD_TYPE_CREATE_NAME_REPEAT(40001,"模型类型已存在"),
    MINECRAFT_MOD_TYPE_UPDATE_ENTITY_NONE(40002,"找不到模型类型"),
    MINECRAFT_MOD_TYPE_UPDATE_NAME_REPEAT(40002,"模型类型已存在");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
