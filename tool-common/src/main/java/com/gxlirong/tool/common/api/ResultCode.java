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
    //文件
    FILE_DIRECTORY_CREATE_ERROR(40401, "文件夹创建失败"),
    //我的世界-模型类型
    MINECRAFT_MOD_CATEGORY_CREATE_NAME_REPEAT(40001, "模型类型已存在"),
    MINECRAFT_MOD_CATEGORY_UPDATE_ENTITY_NONE(40002, "找不到模型类型"),
    MINECRAFT_MOD_CATEGORY_UPDATE_NAME_REPEAT(40003, "模型类型已存在"),
    //我的世界-模型
    MINECRAFT_MOD_CREATE_NAME_REPEAT(40101, "模型已存在"),
    MINECRAFT_MOD_CREATE_CATEGORY_ID_NONE(40102, "找不到模型类型"),
    MINECRAFT_MOD_CREATE_CATEGORY_FILE_EXTENSION(40103, "文件类型错误"),
    //我的世界-模型-汉化队列
    MINECRAFT_MOD_CHINESE_MOD_NONE(1, "找不到模型"),
    MINECRAFT_MOD_CHINESE_FILE_NONE(2, "找不到文件"),
    MINECRAFT_MOD_CHINESE_CREATE_CHINESE_ERROR(2, "lang文件内容汉化失败"),
    //我的世界-模型-提取队列
    MINECRAFT_MOD_LANG_MOD_NONE(1, "找不到模型"),
    MINECRAFT_MOD_LANG_FILE_NONE(2, "找不到文件"),
    MINECRAFT_MOD_LANG_FILE_CONTENT_NONE(3, "lang文件内容读取失败"),
    MINECRAFT_MOD_LANG_FILE_CREATE_ERROR(4, "lang文件内容存储失败"),
    MINECRAFT_MOD_LANG_FILE_CREATE_INFO_ERROR(5, "创建mod信息失败"),


    MINECRAFT_MOD_CREATE_CATEGORY_FILE_NONE_LANG(40104, "文件中不存在lang文件"),
    MINECRAFT_MOD_CREATE_CATEGORY_FILE_ERROR(40105, "操作文件失败"),
    ;

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
