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
import com.gxlirong.tool.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private UserUtils userUtils;

    /**
     * 列表
     *
     * @param minecraftModCategoryQueryParam 查询参数
     * @return IPage<ToolMinecraftModCategory>
     * @author lirong
     */
    @Override
    public IPage<ToolMinecraftModCategory> getList(ToolMinecraftModCategoryQueryParam minecraftModCategoryQueryParam) {
        return this.page(
                new Page<>(minecraftModCategoryQueryParam.getPageNum(), minecraftModCategoryQueryParam.getPageSize()),
                new QueryWrapper<ToolMinecraftModCategory>()
                        .like(minecraftModCategoryQueryParam.getName() != null, "name", minecraftModCategoryQueryParam.getName())
                        .eq("domain", userUtils.getUser().getDomain())
                        .eq("organization_id", userUtils.getUser().getOrganizationId())
                        .eq("is_deleted", false)
                        .orderByDesc("created_time"));
    }

    /**
     * 新增
     *
     * @param minecraftModCategoryPostParam 提交表单
     * @return 是否成功
     * @author lirong
     */
    @Override
    public boolean create(ToolMinecraftModCategoryPostParam minecraftModCategoryPostParam) {
        if (this.count(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .eq("domain", userUtils.getUser().getDomain())
                        .eq("organization_id", userUtils.getUser().getOrganizationId())
                        .eq("name", minecraftModCategoryPostParam.getName())
                        .eq("is_deleted", false)
        ) != 0) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_CATEGORY_CREATE_NAME_REPEAT);
        }
        ToolMinecraftModCategory minecraftModCategory = new ToolMinecraftModCategory();
        BeanUtil.copyProperties(minecraftModCategoryPostParam, minecraftModCategory);
        userUtils.insertBefore(minecraftModCategory);
        return this.save(minecraftModCategory);
    }

    /**
     * 修改
     *
     * @param id                            模型类型序号
     * @param minecraftModCategoryPostParam 提交表单
     * @return 是否成功
     * @author lirong
     */
    @Override
    public boolean update(Long id, ToolMinecraftModCategoryPostParam minecraftModCategoryPostParam) {
        ToolMinecraftModCategory minecraftModCategory = this.getOne(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .eq("id", id)
                        .eq("is_deleted", false)
        );
        if (minecraftModCategory == null) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_CATEGORY_UPDATE_ENTITY_NONE);
        }
        if (this.count(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .ne("id", minecraftModCategory.getId())
                        .eq("domain", userUtils.getUser().getDomain())
                        .eq("organization_id", userUtils.getUser().getOrganizationId())
                        .eq("name", minecraftModCategoryPostParam.getName())
                        .eq("is_deleted", false)
        ) != 0) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_CATEGORY_UPDATE_NAME_REPEAT);
        }
        BeanUtil.copyProperties(minecraftModCategoryPostParam, minecraftModCategory);
        userUtils.updateBefore(minecraftModCategory);
        return this.updateById(minecraftModCategory);
    }

    /**
     * 删除
     *
     * @param id 模型类型序号
     * @return 是否成功
     * @author lirong
     */
    @Override
    public boolean delete(Long id) {
        ToolMinecraftModCategory minecraftModCategory = this.getOne(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .eq("id", id)
                        .eq("is_deleted", false)
        );
        minecraftModCategory.setIsDeleted(null);
        userUtils.deleteBefore(minecraftModCategory);
        return this.updateById(minecraftModCategory);
    }

    @Override
    public List<ToolMinecraftModCategory> getAll() {
        return this.list(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .eq("domain", userUtils.getUser().getDomain())
                        .eq("organization_id", userUtils.getUser().getOrganizationId())
                        .eq("is_deleted", false)
        );
    }
}
