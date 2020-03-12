package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.entity.ToolMinecraftModInfo;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lirong
 * @since 2020-03-12
 */
public interface ToolMinecraftModInfoService extends IService<ToolMinecraftModInfo> {
    /**
     * 创建mod信息
     * @param minecraftMod minecraftMod
     * @param path path
     * @return boolean
     */
    boolean create(ToolMinecraftMod minecraftMod, String path) throws IOException;
}
