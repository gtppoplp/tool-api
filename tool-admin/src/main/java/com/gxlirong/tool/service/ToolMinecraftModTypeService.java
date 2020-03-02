package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.domain.dto.ToolMinecraftModTypePostParam;
import com.gxlirong.tool.domain.dto.ToolMinecraftModTypeQueryParam;
import com.gxlirong.tool.entity.ToolMinecraftModTypeEntity;

/**
 * <p>
 * 我的世界模组 服务类
 * </p>
 *
 * @author lirong
 * @since 2020-03-01
 */
public interface ToolMinecraftModTypeService extends IService<ToolMinecraftModTypeEntity> {

    IPage<ToolMinecraftModTypeEntity> getList(ToolMinecraftModTypeQueryParam minecraftModTypeQueryParam);

    boolean create(ToolMinecraftModTypePostParam minecraftModTypePostParam);

    boolean update(Long id, ToolMinecraftModTypePostParam minecraftModTypePostParam);

    boolean delete(Long id);

}
