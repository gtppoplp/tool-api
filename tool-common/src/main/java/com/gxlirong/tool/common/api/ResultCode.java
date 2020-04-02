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
    MINECRAFT_MOD_CATEGORY_NAME_REPEAT(40001, "模型类型已存在"),
    MINECRAFT_MOD_CATEGORY_ENTITY_NONE(40002, "找不到模型类型"),
    //我的世界-模型
    MINECRAFT_MOD_NAME_REPEAT(40101, "模型已存在"),
    MINECRAFT_MOD_NONE(40102, "找不到模型"),
    MINECRAFT_MOD_CATEGORY_ID_NONE(40103, "找不到模型类型"),
    MINECRAFT_MOD_LANG_NOT_COMPLETE(40104, "字段文内容未读取完成"),
    MINECRAFT_MOD_CHINESE_IS_WORKING(40105, "正在汉化中请勿重复提交"),
    MINECRAFT_MOD_FILE_NONE(40106, "找不到文件"),
    MINECRAFT_MOD_LANG_SAVE_ERROR(40107, "汉化字段保存失败"),
    MINECRAFT_MOD_INFO_SAVE_ERROR(40108, "模型信息保存失败"),
    MINECRAFT_MOD_FILE_NONE_LANG(40109, "文件中不存在lang文件"),
    MINECRAFT_MOD_FILE_EXTENSION(40110, "文件类型错误"),
    MINECRAFT_MOD_LANG_NONE(40111, "lang内容不存在"),
    MINECRAFT_MOD_CHINESE_MAX_RETRY(40112, "汉化失败超过最大重试次数"),
    MINECRAFT_MOD_LANG_NOT_FAIL(40113, "只有读取lang提取失败的情况才可设置为无法汉化");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
