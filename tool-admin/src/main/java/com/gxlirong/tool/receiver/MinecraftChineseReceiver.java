package com.gxlirong.tool.receiver;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.entity.ToolFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.entity.ToolMinecraftModLang;
import com.gxlirong.tool.enums.LogEnum;
import com.gxlirong.tool.enums.ToolMinecraftModFileEnum;
import com.gxlirong.tool.service.ToolFileService;
import com.gxlirong.tool.service.ToolLogService;
import com.gxlirong.tool.service.ToolMinecraftModLangService;
import com.gxlirong.tool.service.ToolMinecraftModService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 我的世界汉化消息的处理者
 *
 * @author lirong
 */
@Component
@RabbitListener(queues = "mod.minecraft.chinese")
@Slf4j
public class MinecraftChineseReceiver {

    @Autowired
    private ToolFileService fileService;
    @Autowired
    private ToolMinecraftModService minecraftModService;
    @Autowired
    private ToolMinecraftModLangService minecraftModLangService;
    @Autowired
    private ToolLogService logService;

    @RabbitHandler
    public void handle(Long minecraftModId) {
        try {
            ToolMinecraftMod minecraftMod = minecraftModService.getOne(
                    new QueryWrapper<ToolMinecraftMod>()
                            .eq("id", minecraftModId)
                            .eq("is_deleted", false)
            );
            if (minecraftMod == null) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_CHINESE_MOD_NONE);
            }
            ToolFile file = fileService.getOne(
                    new QueryWrapper<ToolFile>()
                            .eq("table_id", minecraftMod.getId())
                            .eq("entity_name", minecraftMod.getClass().getSimpleName())
                            .eq("category", ToolMinecraftModFileEnum.CATEGORY_PERMANENT.getCategory())
                            .eq("is_deleted", false)
            );
            if (file == null) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_CHINESE_FILE_NONE);
            }
            //获得未汉化的字段
            List<ToolMinecraftModLang> notChineseList = minecraftModLangService.getNotChineseList(minecraftMod.getId());
            if (notChineseList != null) {
                minecraftModLangService.chineseLangList(notChineseList);
                if (!minecraftModLangService.updateBath(notChineseList)) {
                    throw new OperationException(ResultCode.MINECRAFT_MOD_CHINESE_CREATE_CHINESE_ERROR);
                }
            }
        } catch (Exception e) {
            //存储日志到数据库,建立关联
            logService.create(LogEnum.LOG_TYPE_ERROR.getType(), minecraftModId, ToolMinecraftMod.class.getSimpleName(), e.getMessage());
            log.error("错误:" + e.getMessage());
        }
    }
}
