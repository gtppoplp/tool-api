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

    boolean createBath(Long id, List<String> langList);

    boolean updateBath(List<ToolMinecraftModLang> langList);

    void chineseLangList(List<ToolMinecraftModLang> langList) throws InterruptedException;
}
