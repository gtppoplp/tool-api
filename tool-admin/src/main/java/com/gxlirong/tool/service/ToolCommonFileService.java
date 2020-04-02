package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.domain.dto.MinecraftModFileInfo;
import com.gxlirong.tool.entity.ToolCommonFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.entity.ToolMinecraftModLang;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author lirong
 * @since 2020-03-07
 */
public interface ToolCommonFileService extends IService<ToolCommonFile> {

    /**
     * 获得我的世界常驻附件
     *
     * @param minecraftMod minecraftMod
     * @return ToolCommonFile
     */
    ToolCommonFile findByToolMinecraftModPermanent(ToolMinecraftMod minecraftMod);

    /**
     * 获得我的世界模组配置列表
     *
     * @param path 路径(文件夹路径,需要解压之后的)
     * @return 我的世界模组配置列表
     */
    MinecraftModFileInfo getMinecraftModFileInfo(String path) throws IOException;

    /**
     * 我的世界mod文件创建
     *
     * @param minecraftMod ToolMinecraftMod
     * @param tempPath     文件名(不带路径)
     * @param fileName     真实文件名
     * @return 是否成功
     */
    boolean minecraftModCreate(ToolMinecraftMod minecraftMod, String tempPath, String fileName);

    /**
     * 解压zip
     *
     * @param filePath 常驻附件位置
     * @return 返回解压好的路径
     */
    String decompressionZip(String filePath);

    /**
     * 我的世界mod从附件解压并获取狼lang内容
     *
     * @param filePath     常驻附件位置
     * @param langFileName lang文件名
     * @return 返回内容行
     */
    List<String> minecraftModLangFromFilePath(String filePath, String langFileName) throws IOException;

    /**
     * 我的世界mod将汉化内容写入到汉化文件
     *
     * @param notChineseList notChineseList
     * @param filePathName  文件夹名称
     */
    void minecraftModPackageEnLangFromChineseList(List<ToolMinecraftModLang> notChineseList, String filePathName);

    /**
     * 我的世界mod将汉化内容打包到jar
     *
     * @param path 文件名(完整路径,包括后缀)
     */
    void minecraftModPackageFromPath(String path);

    /**
     * 我的世界mod复制mod到游戏目录
     *
     * @param minecraftMod ToolMinecraftMod
     * @param path         文件名(不带路径)
     * @return 是否成功
     */
    boolean minecraftModUse(ToolMinecraftMod minecraftMod, String path);
}
