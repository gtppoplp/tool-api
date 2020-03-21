package com.gxlirong.tool.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.domain.dto.ToolMinecraftModLangPostParam;
import com.gxlirong.tool.domain.dto.ToolMinecraftModPostParam;
import com.gxlirong.tool.domain.dto.ToolMinecraftModQueryParam;
import com.gxlirong.tool.entity.ToolCommonLog;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.entity.ToolMinecraftModCategory;
import com.gxlirong.tool.entity.ToolMinecraftModLang;
import com.gxlirong.tool.enums.QueueEnum;
import com.gxlirong.tool.enums.ToolMinecraftModChineseEnum;
import com.gxlirong.tool.enums.ToolMinecraftModLangEnum;
import com.gxlirong.tool.mapper.ToolMinecraftModMapper;
import com.gxlirong.tool.service.*;
import com.gxlirong.tool.utils.UserUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class ToolMinecraftModServiceImpl extends ServiceImpl<ToolMinecraftModMapper, ToolMinecraftMod> implements ToolMinecraftModService {

    @Autowired
    private UserUtils userUtils;
    @Autowired
    private ToolMinecraftModCategoryService minecraftModCategoryService;
    @Autowired
    private ToolCommonFileService fileService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ToolCommonLogService commonLogService;
    @Autowired
    private ToolMinecraftModLangService minecraftModLangService;

    /**
     * 我的世界模组 - 列表
     *
     * @param minecraftModTypeQueryParam minecraftModTypeQueryParam
     * @return IPage<ToolMinecraftMod>
     */
    @Override
    public IPage<ToolMinecraftMod> getList(ToolMinecraftModQueryParam minecraftModTypeQueryParam) {
        return this.page(
                new Page<>(minecraftModTypeQueryParam.getPageNum(), minecraftModTypeQueryParam.getPageSize()),
                new QueryWrapper<ToolMinecraftMod>()
                        .like(minecraftModTypeQueryParam.getName() != null, "name", minecraftModTypeQueryParam.getName())
                        .like(minecraftModTypeQueryParam.getCategoryId() != null, "category_id", minecraftModTypeQueryParam.getCategoryId())
                        .like(minecraftModTypeQueryParam.getLangStatus() != null, "lang_status", minecraftModTypeQueryParam.getLangStatus())
                        .like(minecraftModTypeQueryParam.getChineseStatus() != null, "chinese_status", minecraftModTypeQueryParam.getChineseStatus())
                        .like(minecraftModTypeQueryParam.getEnabledStatus() != null, "enabled_status", minecraftModTypeQueryParam.getEnabledStatus())
                        .eq("is_deleted", false)
                        .orderByDesc("created_time"));
    }

    @Override
    public ToolMinecraftMod findById(Long id) {
        ToolMinecraftMod minecraftMod = this.getOne(
                new QueryWrapper<ToolMinecraftMod>()
                        .eq("id", id)
                        .eq("is_deleted", false)
        );
        if (minecraftMod == null) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_NONE);
        }
        return minecraftMod;
    }


    /**
     * 我的世界模组 - 新增
     *
     * @param minecraftModTypePostParam minecraftModTypePostParam
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
            //重复名字
            throw new OperationException(ResultCode.MINECRAFT_MOD_NAME_REPEAT);
        }
        if (minecraftModCategoryService.count(
                new QueryWrapper<ToolMinecraftModCategory>()
                        .eq("id", minecraftModTypePostParam.getCategoryId())
                        .eq("is_deleted", false)
        ) == 0) {
            //找不到类型
            throw new OperationException(ResultCode.MINECRAFT_MOD_CATEGORY_ID_NONE);
        }
        ToolMinecraftMod minecraftMod = new ToolMinecraftMod();
        BeanUtil.copyProperties(minecraftModTypePostParam, minecraftMod);
        userUtils.insertBefore(minecraftMod);
        if (this.save(minecraftMod)) {
            //保存文件到常驻目录
            return fileService.minecraftModCreate(minecraftMod, minecraftModTypePostParam.getPath(), minecraftModTypePostParam.getFileName());
        }
        return true;
    }

    /**
     * 我的世界模组 - 汉化
     *
     * @param id id
     * @return boolean
     */
    @Override
    public boolean chinese(Long id) {
        ToolMinecraftMod minecraftMod = this.getOne(
                new QueryWrapper<ToolMinecraftMod>()
                        .eq("id", id)
                        .eq("is_deleted", false)
        );
        if (minecraftMod == null) {
            //模型不存在
            throw new OperationException(ResultCode.MINECRAFT_MOD_NONE);
        }
        if (!minecraftMod.getLangStatus().equals(ToolMinecraftModLangEnum.LANG_STATUS.getLangComplete())) {
            //字段未读取完成就汉化
            throw new OperationException(ResultCode.MINECRAFT_MOD_LANG_NOT_COMPLETE);
        }
        if (minecraftMod.getChineseStatus().equals(ToolMinecraftModChineseEnum.CHINESE_STATUS.getChineseWorking())) {
            //汉化进行中
            throw new OperationException(ResultCode.MINECRAFT_MOD_CHINESE_IS_WORKING);
        }
        minecraftMod.setChineseStatus(ToolMinecraftModChineseEnum.CHINESE_STATUS.getChineseWorking());
        userUtils.updateBefore(minecraftMod);
        if (this.updateById(minecraftMod)) {
            rabbitTemplate.convertAndSend(QueueEnum.QUEUE_MINECRAFT_CHINESE.getRouteKey(), id);
            return true;
        }
        return false;
    }


    /**
     * 我的世界模组 - 删除
     *
     * @param id id
     * @return boolean
     */
    @Override
    public boolean delete(Long id) {
        return false;
    }

    /**
     * 我的世界模组 - 更新
     *
     * @param id id
     * @return boolean
     */
    @Override
    public boolean update(Long id, ToolMinecraftModPostParam minecraftModTypePostParam) {
        return false;
    }

    /**
     * 我的世界模组 - 读取lang
     *
     * @param id id
     * @return boolean
     */
    @Override
    public boolean lang(Long id) {
        ToolMinecraftMod minecraftMod = this.getOne(new QueryWrapper<ToolMinecraftMod>().eq("id", id).eq("is_deleted", false));
        minecraftMod.setLangStatus(ToolMinecraftModLangEnum.LANG_STATUS.getLangWorking());
        userUtils.updateBefore(minecraftMod);
        if (this.updateById(minecraftMod)) {
            rabbitTemplate.convertAndSend(QueueEnum.QUEUE_MINECRAFT_LANG_CREATE.getRouteKey(), id);
            return true;
        }
        return false;
    }

    /**
     * 我的世界模组 - 读取错误日志
     *
     * @param id id
     * @return List<ToolCommonLog>
     */
    @Override
    public List<ToolCommonLog> getLogList(Long id) {
        return commonLogService.getList(id, ToolMinecraftMod.class.getSimpleName());
    }

    /**
     * 我的世界模组 - lang内容所有列表
     *
     * @param id id
     * @return List<ToolMinecraftModLang>
     */
    @Override
    public List<ToolMinecraftModLang> getLangList(Long id) {
        return minecraftModLangService.getList(id);
    }

    /**
     * 我的世界模组 - 编辑lang内容
     *
     * @param minecraftModTypePostParam minecraftModTypePostParam
     * @return boolean
     */
    @Override
    public boolean updateLang(Long id, ToolMinecraftModLangPostParam minecraftModTypePostParam) {
        return minecraftModLangService.update(id, minecraftModTypePostParam);
    }
}
