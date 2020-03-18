package com.gxlirong.tool.receiver;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.entity.ToolCommonFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.enums.LogEnum;
import com.gxlirong.tool.enums.ToolMinecraftModFileEnum;
import com.gxlirong.tool.enums.ToolMinecraftModLangEnum;
import com.gxlirong.tool.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    private ToolCommonFileService fileService;
    @Autowired
    private ToolMinecraftModService minecraftModService;
    @Autowired
    private ToolMinecraftModLangService minecraftModLangService;
    @Autowired
    private ToolCommonLogService logService;
    @Autowired
    private ToolMinecraftModInfoService minecraftModInfoService;

    @RabbitHandler
    public void handle(Long minecraftModId) {
        ToolMinecraftMod minecraftMod = null;
        try {
            //读取mod
            minecraftMod = minecraftModService.findById(minecraftModId);
            //读取附件
            ToolCommonFile file = fileService.findByToolMinecraftModPermanent(minecraftMod);
            //解压mod 附件
            String decompressionPath = fileService.decompressionZip(file.getPath());
            //保存mod info信息
            if (!minecraftModInfoService.create(minecraftMod, decompressionPath)) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_INFO_SAVE_ERROR);
            }
            //读取英文参数列表
            List<String> enUSStringList = fileService.minecraftModLangFromFilePath(decompressionPath, "en_us.lang");
            if (enUSStringList == null) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_FILE_NONE_LANG);
            }
            //读取中文参数列表
            List<String> zhCNStringList = fileService.minecraftModLangFromFilePath(decompressionPath, "zh_cn.lang");
            //合并英中参数 如果zh不存在en的字段,则将en的字段添加到zh
            if (zhCNStringList == null) {
                zhCNStringList = new ArrayList<>();
            }
            for (String enUSString : enUSStringList) {
                boolean zhHaveValue = false;
                for (String zhCNString : zhCNStringList) {
                    if (enUSString.contains("=") &&
                            zhCNString.contains("=") &&
                            enUSString.substring(0, enUSString.indexOf("=")).equals(zhCNString.substring(0, zhCNString.indexOf("=")))) {
                        zhHaveValue = true;
                        break;
                    }
                }
                if (!zhHaveValue) {
                    zhCNStringList.add(enUSString);
                }
            }
            if (!minecraftModLangService.createBath(minecraftMod.getId(), enUSStringList, zhCNStringList)) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_LANG_SAVE_ERROR);
            }
            minecraftMod.setLangStatus(ToolMinecraftModLangEnum.LANG_STATUS.getLangComplete());
            minecraftModService.updateById(minecraftMod);
            log.error("我的世界字段对应消息的处理完成");
        } catch (Exception e) {
            if (minecraftMod != null) {
                minecraftMod.setLangStatus(ToolMinecraftModLangEnum.LANG_STATUS.getLangFail());
                minecraftModService.updateById(minecraftMod);
            }
            //存储日志到数据库
            logService.create(LogEnum.LOG_TYPE_ERROR.getType(), minecraftModId, ToolMinecraftMod.class.getSimpleName(), e.getMessage());
            log.error("错误:" + e.getMessage());
        }
    }
}
