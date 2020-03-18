package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.entity.ToolMinecraftModLang;

import java.util.List;

/**
 * <p>
 * 我的世界模组表 服务类
 * </p>
 *
 * @author lirong
 * @since 2020-03-11
 */
public interface ToolMinecraftModLangService extends IService<ToolMinecraftModLang> {

    /**
     * 我的世界模组lang 获得未汉化列表
     *
     * @param id id
     * @return List<ToolMinecraftModLang>
     */
    List<ToolMinecraftModLang> getNotChineseList(Long id);

    List<ToolMinecraftModLang> getChineseList(Long id);

    /**
     * 我的世界模组lang字段批量创建
     *
     * @param id             模组标识
     * @param enUSStringList enUSStringList
     * @param zhCNStringList zhCNStringList
     * @return boolean
     */
    boolean createBath(Long id, List<String> enUSStringList, List<String> zhCNStringList);

    /**
     * 汉化字段
     *
     * @param langList langList
     * @throws InterruptedException InterruptedException
     */
    void chineseLang(ToolMinecraftModLang langList) throws InterruptedException;
}
