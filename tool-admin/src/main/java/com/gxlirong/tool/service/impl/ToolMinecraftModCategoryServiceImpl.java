package com.gxlirong.tool.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.domain.dto.ToolMinecraftModCategoryPostParam;
import com.gxlirong.tool.domain.dto.ToolMinecraftModCategoryQueryParam;
import com.gxlirong.tool.entity.ToolMinecraftModCategory;
import com.gxlirong.tool.mapper.ToolMinecraftModCategoryMapper;
import com.gxlirong.tool.service.ToolMinecraftModCategoryService;
import com.gxlirong.tool.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 我的世界模组 服务实现类
 * </p>
 *
 * @author lirong
 * @since 2020-03-01
 */
@Service
public class ToolMinecraftModCategoryServiceImpl extends ServiceImpl<ToolMinecraftModCategoryMapper, ToolMinecraftModCategory> implements ToolMinecraftModCategoryService {

    @Autowired
    private UserUtil userUtil;

    @Override
    public IPage<ToolMinecraftModCategory> getList(ToolMinecraftModCategoryQueryParam minecraftModTypeQueryParam) {
        return this.page(
                new Page<>(minecraftModTypeQueryParam.getCurrent(), minecraftModTypeQueryParam.getSize()),
                new QueryWrapper<ToolMinecraftModCategory>()
                        .like(minecraftModTypeQueryParam.getName() != null, "type_name", minecraftModTypeQueryParam.getName())
                        .eq("is_deleted", false)
                        .orderByDesc("created_time"));
    }

    @Override
    public boolean create(ToolMinecraftModCategoryPostParam minecraftModTypePostParam) {
        if (this.count(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .eq("domain", userUtil.getUser().getDomain())
                        .eq("organization_id", userUtil.getUser().getOrganizationId())
                        .eq("name", minecraftModTypePostParam.getName())
                        .eq("is_deleted", false)
        ) != 0) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_TYPE_CREATE_NAME_REPEAT);
        }
        ToolMinecraftModCategory minecraftModType = new ToolMinecraftModCategory();
        BeanUtil.copyProperties(minecraftModTypePostParam, minecraftModType);
        userUtil.insertBefore(minecraftModType);
        return this.save(minecraftModType);
    }

    @Override
    public boolean update(Long id, ToolMinecraftModCategoryPostParam minecraftModTypePostParam) {
        ToolMinecraftModCategory minecraftModType = this.getOne(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .eq("id", id)
                        .eq("is_deleted", false)
        );
        if (minecraftModType == null) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_TYPE_UPDATE_ENTITY_NONE);
        }
        if (this.count(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .ne("id", minecraftModType.getId())
                        .eq("domain", userUtil.getUser().getDomain())
                        .eq("organization_id", userUtil.getUser().getOrganizationId())
                        .eq("name", minecraftModTypePostParam.getName())
                        .eq("is_deleted", false)
        ) != 0) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_TYPE_UPDATE_NAME_REPEAT);
        }
        BeanUtil.copyProperties(minecraftModTypePostParam, minecraftModType);
        userUtil.updateBefore(minecraftModType);
        return this.updateById(minecraftModType);
    }

    @Override
    public boolean delete(Long id) {
        ToolMinecraftModCategory minecraftModType = this.getOne(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .eq("id", id)
                        .eq("is_deleted", false)
        );
        minecraftModType.setIsDeleted(null);
        userUtil.deleteBefore(minecraftModType);
        return this.updateById(minecraftModType);
    }
}
