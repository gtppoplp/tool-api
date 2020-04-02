package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.domain.dto.MinecraftModFileInfo;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.entity.ToolMinecraftModInfo;
import com.gxlirong.tool.mapper.ToolMinecraftModInfoMapper;
import com.gxlirong.tool.service.ToolCommonFileService;
import com.gxlirong.tool.service.ToolMinecraftModInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lirong
 * @since 2020-03-12
 */
@Service
public class ToolMinecraftModInfoServiceImpl extends ServiceImpl<ToolMinecraftModInfoMapper, ToolMinecraftModInfo> implements ToolMinecraftModInfoService {
    @Autowired
    private ToolCommonFileService fileService;

    /**
     * 创建mod信息
     *
     * @param minecraftMod minecraftMod
     * @param path         path
     * @return boolean
     */
    @Override
    public boolean create(ToolMinecraftMod minecraftMod, String path) throws IOException {
        ArrayList<ToolMinecraftModInfo> minecraftModInfoList = new ArrayList<>();

        MinecraftModFileInfo minecraftModFileInfo = fileService.getMinecraftModFileInfo(path);
        ToolMinecraftModInfo toolMinecraftModInfo = new ToolMinecraftModInfo();
        this.remove(new QueryWrapper<ToolMinecraftModInfo>().eq("minecraft_mod_id", minecraftMod.getId()));
        toolMinecraftModInfo.setMinecraftModId(minecraftMod.getId());
        toolMinecraftModInfo.setModId(minecraftModFileInfo.getModid().toLowerCase());
        toolMinecraftModInfo.setName(minecraftModFileInfo.getName());
        toolMinecraftModInfo.setDescription(minecraftModFileInfo.getDescription());
        toolMinecraftModInfo.setVersion(minecraftModFileInfo.getVersion());
        toolMinecraftModInfo.setMcVersion(minecraftModFileInfo.getMcversion());
        toolMinecraftModInfo.setUrl(minecraftModFileInfo.getUrl());
        toolMinecraftModInfo.setAuthorList(minecraftModFileInfo.getAuthorList());
        toolMinecraftModInfo.setDependencies(minecraftModFileInfo.getDependencies());
        minecraftModInfoList.add(toolMinecraftModInfo);

        return this.saveBatch(minecraftModInfoList);
    }
}
