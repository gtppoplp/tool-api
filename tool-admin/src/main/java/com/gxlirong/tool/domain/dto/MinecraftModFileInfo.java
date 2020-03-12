package com.gxlirong.tool.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * 我的世界mod配置文件信息
 *
 * @author lirong
 */
@Data
public class MinecraftModFileInfo {
    private String modid;
    private String name;
    private String description;
    private String version;
    private String mcversion;
    private String url;
    private String updateUrl;
    private List<String> authorList;
    private String credits;
    private String logoFile;
    private List<String> screenshots;
    private List<String> dependencies;
}
