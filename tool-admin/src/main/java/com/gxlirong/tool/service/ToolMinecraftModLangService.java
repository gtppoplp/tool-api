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

    boolean updateBath(List<ToolMinecraftModLang> langList);

    void chineseLangList(List<ToolMinecraftModLang> langList) throws InterruptedException;
}
