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

    /**
     * 我的世界模组 - 列表
     *
     * @param minecraftModTypeQueryParam minecraftModTypeQueryParam
     * @return IPage<ToolMinecraftMod>
     */
    IPage<ToolMinecraftMod> getList(ToolMinecraftModQueryParam minecraftModTypeQueryParam);

    /**
     * 我的世界模组 - 根据id获得实体
     * @param id id
     * @return ToolMinecraftMod
     */
    ToolMinecraftMod findById(Long id);

    /**
     * 我的世界模组 - 新增
     *
     * @param minecraftModTypePostParam minecraftModTypePostParam
     * @return boolean
     */
    boolean create(ToolMinecraftModPostParam minecraftModTypePostParam);

    /**
     * 我的世界模组 - 汉化
     *
     * @param id id
     * @return boolean
     */
    boolean chinese(Long id);

    /**
     * 我的世界模组 - 删除
     *
     * @param id id
     * @return boolean
     */
    boolean delete(Long id);

    /**
     * 我的世界模组 - 更新
     *
     * @param id id
     * @return boolean
     */
    boolean update(Long id, ToolMinecraftModPostParam minecraftModTypePostParam);

    /**
     * 我的世界模组 - 读取lang
     *
     * @param id id
     * @return boolean
     */
    boolean lang(Long id);
}
