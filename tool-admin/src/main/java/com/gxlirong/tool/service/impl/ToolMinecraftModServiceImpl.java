package com.gxlirong.tool.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.domain.dto.ToolMinecraftModPostParam;
import com.gxlirong.tool.domain.dto.ToolMinecraftModQueryParam;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.entity.ToolMinecraftModCategory;
import com.gxlirong.tool.mapper.ToolMinecraftModMapper;
import com.gxlirong.tool.service.ToolFileService;
import com.gxlirong.tool.service.ToolMinecraftModCategoryService;
import com.gxlirong.tool.service.ToolMinecraftModService;
import com.gxlirong.tool.utils.UserUtils;
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
public class ToolMinecraftModServiceImpl extends ServiceImpl<ToolMinecraftModMapper, ToolMinecraftMod> implements ToolMinecraftModService {

    @Autowired
    private UserUtils userUtils;
    @Autowired
    private ToolMinecraftModCategoryService minecraftModCategoryService;
    @Autowired
    private ToolFileService fileService;

    /**
     * 列表
     *
     * @param minecraftModTypeQueryParam ToolMinecraftModQueryParam
     * @return IPage<ToolMinecraftMod>
     */
    @Override
    public IPage<ToolMinecraftMod> getList(ToolMinecraftModQueryParam minecraftModTypeQueryParam) {
        return this.page(
                new Page<>(minecraftModTypeQueryParam.getPageNum(), minecraftModTypeQueryParam.getPageSize()),
                new QueryWrapper<ToolMinecraftMod>()
                        .like(minecraftModTypeQueryParam.getName() != null, "name", minecraftModTypeQueryParam.getName())
                        .eq("is_deleted", false)
                        .orderByDesc("created_time"));
    }

    /**
     * 新增
     *
     * @param minecraftModTypePostParam ToolMinecraftModPostParam
     * @return boolean
     */
    @Override
    public boolean create(ToolMinecraftModPostParam minecraftModTypePostParam) {
        if (this.count(
                new QueryWrapper<ToolMinecraftMod>()
                        .eq("domain", userUtils.getUser().getDomain())
                        .eq("organization_id", userUtils.getUser().getOrganizationId())
                        .eq("name", minecraftModTypePostParam.getName())
                        .eq("is_deleted", false)
        ) != 0) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_CATEGORY_CREATE_NAME_REPEAT);
        }
        if (minecraftModCategoryService.count(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .eq("id", minecraftModTypePostParam.getCategoryId())
                        .eq("is_deleted", false)
        ) == 0) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_CREATE_CATEGORY_ID_NONE);
        }
        ToolMinecraftMod minecraftMod = new ToolMinecraftMod();
        BeanUtil.copyProperties(minecraftModTypePostParam, minecraftMod);
        userUtils.insertBefore(minecraftMod);
        //测试文件上传
        if (this.save(minecraftMod)) {
            //文件移动操作
            if (fileService.minecraftModCreate(minecraftMod, minecraftModTypePostParam.getPath())) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(Long id, ToolMinecraftModPostParam minecraftModTypePostParam) {
        return false;
    }
}
