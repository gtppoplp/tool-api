package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.config.FileUploadConfig;
import com.gxlirong.tool.domain.dto.ChineseTranslate;
import com.gxlirong.tool.domain.dto.MinecraftModFileInfo;
import com.gxlirong.tool.entity.ToolFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.enums.ToolMinecraftModFileEnum;
import com.gxlirong.tool.mapper.ToolFileMapper;
import com.gxlirong.tool.service.ToolFileService;
import com.gxlirong.tool.utils.ChineseUtils;
import com.gxlirong.tool.utils.FileUtils;
import com.gxlirong.tool.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zeroturnaround.zip.ZipUtil;

import java.io.*;
import java.util.List;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author lirong
 * @since 2020-03-07
 */
@Service
@Slf4j
public class ToolFileServiceImpl extends ServiceImpl<ToolFileMapper, ToolFile> implements ToolFileService {
    @Autowired
    private FileUploadConfig fileUploadConfig;
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private ChineseUtils chineseUtils;
    @Autowired
    private UserUtils userUtils;

    /**
     * 获得文件实体
     *
     * @param minecraftMod ToolMinecraftMod
     * @param path         文件名(不带路径)
     * @return 文件实体
     */
    private ToolFile getCreateToolFile(ToolMinecraftMod minecraftMod, String path) {
        ToolFile toolFile = new ToolFile();
        toolFile.setName(path.substring(0, path.lastIndexOf(".") + 1));
        toolFile.setExtension(path.substring(path.lastIndexOf(".") + 1));
        toolFile.setTableId(minecraftMod.getId());
        toolFile.setEntityName(minecraftMod.getClass().getSimpleName());
        toolFile.setCategory(ToolMinecraftModFileEnum.CATEGORY_GAME.getCategory());
        userUtils.insertBefore(toolFile);
        return toolFile;
    }

    /**
     * 我的世界mod文件创建
     *
     * @param minecraftMod ToolMinecraftMod
     * @param path         文件名(不带路径)
     * @return 是否成功
     */
    @Override
    public boolean minecraftModCreate(ToolMinecraftMod minecraftMod, String path) {
        if (!path.substring(path.lastIndexOf(".") + 1).equals("jar")) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_CREATE_CATEGORY_FILE_EXTENSION);
        }
        //读取内容并汉化(判断是否需要汉化)
        if (minecraftMod.getIsChinese()) {
            this.minecraftModChinese(minecraftMod, path);
        }
        ToolFile toolFile = this.getCreateToolFile(minecraftMod, path);
        toolFile.setPath(fileUploadConfig.getFilePath() + path);
        if (!this.save(toolFile)) {
            return false;
        }
        //移动到游戏文件夹
        if (minecraftMod.getIsEnabled()) {
            return this.minecraftModUse(minecraftMod, path);
        }
        return true;
    }

    /**
     * 我的世界mod复制mod到游戏目录
     *
     * @param minecraftMod ToolMinecraftMod
     * @param path         文件名(不带路径)
     * @return 是否成功
     */
    @Override
    public boolean minecraftModUse(ToolMinecraftMod minecraftMod, String path) {
        //移动到游戏目录
        fileUtils.copy(fileUploadConfig.getFilePath() + path, fileUploadConfig.getMinecraftFilePath() + path);
        ToolFile toolFile = this.getCreateToolFile(minecraftMod, path);
        toolFile.setPath(fileUploadConfig.getMinecraftFilePath() + path);
        return this.save(toolFile);
    }

    /**
     * 我的世界mod汉化
     *
     * @param minecraftMod ToolMinecraftMod
     * @param path         文件名(不带路径)
     * @return 是否成功
     */
    @Override
    public boolean minecraftModChinese(ToolMinecraftMod minecraftMod, String path) {
        try {
            File enUSFile = null;
            File zhCNFile = null;
            List<String> zhCNStringList = null;

            //解压
            String extractFolder = fileUploadConfig.getFileTempPath() + path.substring(0, path.lastIndexOf("."));
            ZipUtil.unpack(
                    new File(fileUploadConfig.getFileTempPath() + path),
                    new File(extractFolder)
            );
            log.info("解压完成");
            //查找lang后缀文件名
            List<File> langFileList = fileUtils.searchExtension(new File(
                    fileUploadConfig.getFileTempPath() + path.substring(0, path.lastIndexOf("."))), "lang"
            );
            log.info("查找完成");
            //读取正确的文件
            //读取文件夹下modId.info文件中的json值modId,匹配改路径下的en_us.lang
            List<MinecraftModFileInfo> minecraftModFileInfo = fileUtils.getMinecraftModFileInfo(extractFolder);
            String modId = "";
            if (!minecraftModFileInfo.isEmpty()) {
                modId = minecraftModFileInfo.get(0).getModid();
            }
            for (File langFile : langFileList) {
                if (langFile.getName().toLowerCase().equals("en_us.lang") && langFile.getPath().contains(modId)) {
                    enUSFile = langFile;
                }
                if (langFile.getName().toLowerCase().equals("zh_cn.lang") && langFile.getPath().contains(modId)) {
                    zhCNFile = langFile;
                }
            }
            log.info("获取正确lang完成");
            if (enUSFile == null) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_CREATE_CATEGORY_FILE_NONE_LANG);
            }
            if (zhCNFile == null) {
                fileUtils.copy(enUSFile.getPath(), enUSFile.getParentFile() + "/zh_cn.lang");
            } else {
                zhCNStringList = fileUtils.readerFileStringList(zhCNFile.getPath());
            }
            //读取数据存入list
            List<String> enUSStringList = fileUtils.readerFileStringList(enUSFile.getPath());
            //如果zh存在翻译,则将en需要翻译的行删除,最后enUSStringList与zhCNStringList合并
            if (zhCNStringList != null) {
                for (int i = 0; i < enUSStringList.size(); i++) {
                    for (String zhCNString : zhCNStringList) {
                        String enUSString = enUSStringList.get(i);
                        if (enUSString.contains("=") &&
                                zhCNString.contains("=") &&
                                enUSString.substring(0, enUSString.indexOf("=")).equals(zhCNString.substring(0, zhCNString.indexOf("=")))) {
                            enUSStringList.set(i, zhCNString);
                        }
                    }
                }
            }
            log.info("匹配数据完成");
            enUSStringList = this.chineseStringList(enUSStringList);
            log.info("汉化完成");
            if (!fileUtils.writerFileStringList(enUSStringList, enUSFile.getParentFile() + "/zh_cn.lang")) {
                throw new RuntimeException();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //重新打包并移动到常驻文件夹
        ZipUtil.pack(
                new File(fileUploadConfig.getFileTempPath() + path.substring(0, path.lastIndexOf("."))),
                new File(fileUploadConfig.getFilePath() + path)
        );
        log.info("重新打包并移动到常驻文件夹完成");
        return true;
    }

    /**
     * 将英文列表翻译成中文
     *
     * @param enUSStringList 英文列表
     * @return List<String>
     */
    public List<String> chineseStringList(List<String> enUSStringList) throws InterruptedException {
        for (int i = 0; i < enUSStringList.size(); i++) {
            if (enUSStringList.get(i).contains("=")) {
                //开始翻译
                ChineseTranslate chineseString = chineseUtils.getChineseString(chineseUtils.stringInsertWhitespace(enUSStringList.get(i).substring(enUSStringList.get(i).lastIndexOf("=") + 1)));
                String translationString = enUSStringList.get(i).substring(0, enUSStringList.get(i).lastIndexOf("=") + 1) + chineseString.getTrans_result().get(0).getDst();
                //去空格
                translationString = translationString.replace(" ", "");
                //标点符号转小写
                translationString = chineseUtils.toDBC(translationString);
                enUSStringList.set(i, translationString);
                log.info("{}", translationString);
            }
        }
        return enUSStringList;
    }

    /**
     * 将英文列表翻译成中文
     * 这里采用|分割调用翻译(因为个人翻译qbs只能为1,所以尽量按数量翻译后再解析)
     * 针对百度的api失效,使用该方案,api返回的数据与传输不对应
     *
     * @param enUSStringList 英文列表
     * @return List<String>
     */
    private List<String> chineseStringBathList(List<String> enUSStringList) throws InterruptedException {
        int splicingNumber = 0;//拼接次数
        int startNumber = 0;//开始位置,用于将中文复制给英文字符串
        StringBuilder bathTranslationString = new StringBuilder();
        for (int i = 0; i < enUSStringList.size(); i++) {
            bathTranslationString.append("~").append(enUSStringList.get(i).substring(enUSStringList.get(i).lastIndexOf("=") + 1));
            splicingNumber++;
            if (splicingNumber == 50 || i + 1 == enUSStringList.size()) {
                //开始翻译
                ChineseTranslate chineseString = chineseUtils.getChineseString(chineseUtils.stringInsertWhitespace(bathTranslationString.toString()));
                String dst = chineseString.getTrans_result().get(0).getDst();
                String[] splitArray = dst.split("~");
                //恢复中文字符串
                for (String split : splitArray) {
                    if (!split.equals("")) {
                        if (startNumber >= enUSStringList.size()) {
                            break;
                        }
                        String translationString = enUSStringList.get(startNumber).substring(0, enUSStringList.get(startNumber).lastIndexOf("=") + 1) + split;
                        //去空格
                        translationString = translationString.replace(" ", "");
                        //标点符号转小写
                        translationString = chineseUtils.toDBC(translationString);
                        enUSStringList.set(startNumber, translationString);
                        startNumber++;
                        bathTranslationString = new StringBuilder();
                        log.info("{}", translationString);
                    }
                }
                startNumber = i + 1;
                splicingNumber = 0;
            }
        }
        return enUSStringList;
    }
}
