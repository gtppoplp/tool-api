package com.gxlirong.tool.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件上传配置
 *
 * @author lirong
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadConfig {
    /**
     * 文件临时位置
     */
    private String fileTempPath;
    /**
     * 常驻文件夹
     */
    private String filePath;
    /**
     * 我的世界常驻文件夹
     */
    private String minecraftFilePath;
    /**
     * 我的世界mod文件夹位置
     */
    private String minecraftModFilePath;
}
