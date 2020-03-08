package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.entity.ToolFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;

import java.io.FileNotFoundException;
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
public interface ToolFileService extends IService<ToolFile> {

    /**
     * 我的世界mod文件创建
     *
     * @param minecraftMod ToolMinecraftMod
     * @param path         文件名(不带路径)
     * @return 是否成功
     */
    boolean minecraftModCreate(ToolMinecraftMod minecraftMod, String path);

    /**
     * 我的世界mod汉化
     *
     * @param minecraftMod ToolMinecraftMod
     * @param path         文件名(不带路径)
     * @return 是否成功
     */
    boolean minecraftModChinese(ToolMinecraftMod minecraftMod, String path);

    /**
     * 我的世界mod复制mod到游戏目录
     *
     * @param minecraftMod ToolMinecraftMod
     * @param path         文件名(不带路径)
     * @return 是否成功
     */
    boolean minecraftModUse(ToolMinecraftMod minecraftMod, String path);

    /**
     * 将英文列表翻译成中文
     * 这里采用|分割调用翻译(因为个人翻译qbs只能为1,所以尽量按数量翻译后再解析)
     *
     * @param enUSStringList 英文列表
     * @return List<String>
     */
    List<String> chineseStringList(List<String> enUSStringList) throws InterruptedException;
}
