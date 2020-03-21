package com.gxlirong.tool.service.impl;

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

        List<MinecraftModFileInfo> minecraftModFileInfo = fileService.getMinecraftModFileInfo(path);
        for (MinecraftModFileInfo minecraftModFile : minecraftModFileInfo) {
            ToolMinecraftModInfo toolMinecraftModInfo = new ToolMinecraftModInfo();
            toolMinecraftModInfo.setMinecraftModId(minecraftMod.getId());
            toolMinecraftModInfo.setModid(minecraftModFile.getModid());
            toolMinecraftModInfo.setName(minecraftModFile.getName());
            toolMinecraftModInfo.setDescription(minecraftModFile.getDescription());
            toolMinecraftModInfo.setVersion(minecraftModFile.getVersion());
            toolMinecraftModInfo.setMcversion(minecraftModFile.getMcversion());
            toolMinecraftModInfo.setUrl(minecraftModFile.getUrl());
            toolMinecraftModInfo.setUpdateUrl(minecraftModFile.getUpdateUrl());
            if (minecraftModFile.getAuthors() != null) {
                toolMinecraftModInfo.setAuthorList(String.join(",", minecraftModFile.getAuthors()));
            }
            if (minecraftModFile.getAuthorList() != null) {
                toolMinecraftModInfo.setAuthorList(String.join(",", minecraftModFile.getAuthorList()));
            }
            toolMinecraftModInfo.setCredits(minecraftModFile.getCredits());
            toolMinecraftModInfo.setLogoFile(minecraftModFile.getLogoFile());
            if (minecraftModFile.getScreenshots() != null) {
                toolMinecraftModInfo.setScreenshots(String.join(",", minecraftModFile.getScreenshots()));
            }
            if (minecraftModFile.getScreenshots() != null) {
                toolMinecraftModInfo.setDependencies(String.join(",", minecraftModFile.getDependencies()));
            }
            minecraftModInfoList.add(toolMinecraftModInfo);
        }
        return this.saveBatch(minecraftModInfoList);
    }
}
