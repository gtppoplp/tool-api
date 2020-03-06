package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.domain.dto.ToolMinecraftModCategoryPostParam;
import com.gxlirong.tool.domain.dto.ToolMinecraftModCategoryQueryParam;
import com.gxlirong.tool.entity.ToolMinecraftModCategory;

/**
 * <p>
 * 我的世界模组 服务类
 * </p>
 *
 * @author lirong
 * @since 2020-03-01
 */
public interface ToolMinecraftModCategoryService extends IService<ToolMinecraftModCategory> {

    IPage<ToolMinecraftModCategory> getList(ToolMinecraftModCategoryQueryParam minecraftModTypeQueryParam);

    boolean create(ToolMinecraftModCategoryPostParam minecraftModTypePostParam);

    boolean update(Long id, ToolMinecraftModCategoryPostParam minecraftModTypePostParam);

    boolean delete(Long id);

}
