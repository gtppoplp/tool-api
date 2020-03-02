package com.gxlirong.tool.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.domain.dto.ToolMinecraftModTypePostParam;
import com.gxlirong.tool.domain.dto.ToolMinecraftModTypeQueryParam;
import com.gxlirong.tool.entity.ToolMinecraftModTypeEntity;
import com.gxlirong.tool.mapper.ToolMinecraftModTypeMapper;
import com.gxlirong.tool.service.ToolMinecraftModTypeService;
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
public class ToolMinecraftModTypeServiceImpl extends ServiceImpl<ToolMinecraftModTypeMapper, ToolMinecraftModTypeEntity> implements ToolMinecraftModTypeService {

    @Autowired
    private UserUtil userUtil;

    @Override
    public IPage<ToolMinecraftModTypeEntity> getList(ToolMinecraftModTypeQueryParam minecraftModTypeQueryParam) {
        return this.page(
                new Page<>(minecraftModTypeQueryParam.getCurrent(), minecraftModTypeQueryParam.getSize()),
                new QueryWrapper<ToolMinecraftModTypeEntity>()
                        .like(minecraftModTypeQueryParam.getTypeName() != null, "type_name", minecraftModTypeQueryParam.getTypeName())
                        .orderByDesc("created_at"));
    }

    @Override
    public boolean create(ToolMinecraftModTypePostParam minecraftModTypePostParam) {
        ToolMinecraftModTypeEntity minecraftModTypeEntity = new ToolMinecraftModTypeEntity();
        BeanUtil.copyProperties(minecraftModTypePostParam, minecraftModTypeEntity);
        userUtil.insertBefore(minecraftModTypeEntity);
        return this.save(minecraftModTypeEntity);
    }

    @Override
    public boolean update(Long id, ToolMinecraftModTypePostParam minecraftModTypePostParam) {
        ToolMinecraftModTypeEntity minecraftModTypeEntity = this.getOne(
                new QueryWrapper<ToolMinecraftModTypeEntity>()
                        .eq("type_id", id)
                        .eq("is_deleted", false)
        );
        BeanUtil.copyProperties(minecraftModTypePostParam, minecraftModTypeEntity);
        userUtil.updateBefore(minecraftModTypeEntity);
        return this.updateById(minecraftModTypeEntity);
    }

    @Override
    public boolean delete(Long id) {
        ToolMinecraftModTypeEntity minecraftModTypeEntity = this.getOne(
                new QueryWrapper<ToolMinecraftModTypeEntity>()
                        .eq("type_id", id)
                        .eq("is_deleted", false)
        );
        minecraftModTypeEntity.setIsDeleted(null);
        userUtil.deleteBefore(minecraftModTypeEntity);
        return this.updateById(minecraftModTypeEntity);
    }
}