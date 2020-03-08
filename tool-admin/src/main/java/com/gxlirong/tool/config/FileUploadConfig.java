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
     * 我的世界mod文件夹位置
     */
    private String minecraftFilePath;
}
