package com.gxlirong.tool.receiver;

import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.entity.ToolCommonFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.entity.ToolMinecraftModLang;
import com.gxlirong.tool.enums.LogEnum;
import com.gxlirong.tool.enums.ToolMinecraftModChineseEnum;
import com.gxlirong.tool.service.ToolCommonFileService;
import com.gxlirong.tool.service.ToolCommonLogService;
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
    private ToolMinecraftModService minecraftModService;
    @Autowired
    private ToolMinecraftModLangService minecraftModLangService;
    @Autowired
    private ToolCommonLogService logService;
    @Autowired
    private ToolCommonFileService commonFileService;
    @Autowired
    private ToolCommonFileService fileService;

    @RabbitHandler
    public void handle(Long minecraftModId) {
        ToolMinecraftMod minecraftMod = null;
        try {
            //读取mod
            minecraftMod = minecraftModService.findById(minecraftModId);
            //获得未汉化的字段
            List<ToolMinecraftModLang> notChineseList = minecraftModLangService.getNotChineseList(minecraftMod.getId());
            if (notChineseList != null && !notChineseList.isEmpty()) {
                for (ToolMinecraftModLang notChinese : notChineseList) {
                    minecraftModLangService.chineseLang(notChinese);
                    if (!minecraftModLangService.updateById(notChinese)) {
                        //保存失败
                        throw new OperationException(ResultCode.MINECRAFT_MOD_LANG_SAVE_ERROR);
                    }
                }
            }
            minecraftMod.setChineseStatus(ToolMinecraftModChineseEnum.CHINESE_STATUS.getChineseComplete());
            minecraftModService.updateById(minecraftMod);
            //汉化文件写入
            ToolCommonFile file = fileService.findByToolMinecraftModPermanent(minecraftMod);
            if (file == null) {
                throw new RuntimeException();
            }
            commonFileService.minecraftModPackageEnLangFromChineseList(minecraftModLangService.getList(minecraftMod.getId()), file.getPath().substring(0, file.getPath().lastIndexOf(".")));
            //重新打包
            commonFileService.minecraftModPackageFromPath(commonFileService.findByToolMinecraftModPermanent(minecraftMod).getPath());
            log.error("我的世界汉化字段完成");
        } catch (Exception e) {
            try {
                if (minecraftMod != null) {
                    minecraftMod.setChineseStatus(ToolMinecraftModChineseEnum.CHINESE_STATUS.getChineseFail());
                    minecraftModService.updateById(minecraftMod);
                }
                //存储日志到数据库,建立关联
                logService.create(LogEnum.LOG_TYPE_ERROR.getType(), minecraftModId, ToolMinecraftMod.class.getSimpleName(), e.getMessage());
                log.error("错误:" + e.getMessage());
            } catch (Exception t) {
                t.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
