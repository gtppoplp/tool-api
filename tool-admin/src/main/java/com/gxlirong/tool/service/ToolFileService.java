package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.entity.ToolFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.entity.ToolMinecraftModLang;

import java.util.List;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author lirong
 * @since 2020-03-07
 */
public interface ToolFileService extends IService<ToolFile> {

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
     * 我的世界mod从附件解压并获取狼lang内容
     *
     * @param filePath 常驻附件位置
     * @return 返回内容行
     */
    List<String> minecraftModLangFromFilePath(String filePath);

    /**
     * 我的世界mod将汉化内容打包到jar
     *
     * @param minecraftModLangList 汉化内容列表
     * @param path                 文件名(不带路径)
     * @return 是否成功
     */
    boolean minecraftModPackageFromLang(List<ToolMinecraftModLang> minecraftModLangList, String path);

    /**
     * 我的世界mod复制mod到游戏目录
     *
     * @param minecraftMod ToolMinecraftMod
     * @param path         文件名(不带路径)
     * @return 是否成功
     */
    boolean minecraftModUse(ToolMinecraftMod minecraftMod, String path);
}
