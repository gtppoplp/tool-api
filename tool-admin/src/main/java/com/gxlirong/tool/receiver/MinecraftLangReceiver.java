package com.gxlirong.tool.receiver;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.entity.ToolFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;
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
 * 我的世界字段对应消息的处理者
 *
 * @author lirong
 */
@Component
@RabbitListener(queues = "mod.minecraft.lang_create")
@Slf4j
public class MinecraftLangReceiver {

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
                throw new OperationException(ResultCode.MINECRAFT_MOD_LANG_MOD_NONE);
            }
            ToolFile file = fileService.getOne(
                    new QueryWrapper<ToolFile>()
                            .eq("table_id", minecraftMod.getId())
                            .eq("entity_name", minecraftMod.getClass().getSimpleName())
                            .eq("category", ToolMinecraftModFileEnum.CATEGORY_PERMANENT.getCategory())
                            .eq("is_deleted", false)
            );
            if (file == null) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_LANG_FILE_NONE);
            }
            List<String> langList = fileService.minecraftModLangFromFilePath(file.getPath());
            if (langList == null) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_LANG_FILE_CONTENT_NONE);
            }
            if (!minecraftModLangService.createBath(minecraftMod.getId(), langList)) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_LANG_FILE_CREATE_ERROR);
            }
            log.error("我的世界字段对应消息的处理完成");
        } catch (Exception e) {
            //存储日志到数据库
            logService.create(LogEnum.LOG_TYPE_ERROR.getType(), minecraftModId, ToolMinecraftMod.class.getSimpleName(), e.getMessage());
            log.error("错误:" + e.getMessage());
        }
    }
}
