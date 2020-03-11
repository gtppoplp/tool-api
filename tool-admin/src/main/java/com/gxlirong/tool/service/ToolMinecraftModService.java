package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.domain.dto.ToolMinecraftModPostParam;
import com.gxlirong.tool.domain.dto.ToolMinecraftModQueryParam;
import com.gxlirong.tool.entity.ToolMinecraftMod;

/**
 * <p>
 * 我的世界模组 服务类
 * </p>
 *
 * @author lirong
 * @since 2020-03-01
 */
public interface ToolMinecraftModService extends IService<ToolMinecraftMod> {

    IPage<ToolMinecraftMod> getList(ToolMinecraftModQueryParam minecraftModTypeQueryParam);

    boolean create(ToolMinecraftModPostParam minecraftModTypePostParam);

    boolean chinese(Long id);

    boolean delete(Long id);

    boolean update(Long id, ToolMinecraftModPostParam minecraftModTypePostParam);

    boolean lang(Long id);
}
