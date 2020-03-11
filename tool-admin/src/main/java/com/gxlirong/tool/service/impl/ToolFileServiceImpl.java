package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.config.FileUploadConfig;
import com.gxlirong.tool.domain.dto.ChineseTranslate;
import com.gxlirong.tool.domain.dto.MinecraftModFileInfo;
import com.gxlirong.tool.entity.ToolFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.entity.ToolMinecraftModLang;
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
import java.util.ArrayList;
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
        toolFile.setName(path.substring(0, path.lastIndexOf(".")));
        toolFile.setExtension(path.substring(path.lastIndexOf(".") + 1));
        toolFile.setTableId(minecraftMod.getId());
        toolFile.setEntityName(minecraftMod.getClass().getSimpleName());
        userUtils.insertBefore(toolFile);
        return toolFile;
    }

    /**
     * 我的世界mod文件创建
     *
     * @param minecraftMod ToolMinecraftMod
     * @param tempPath     文件名(不带路径)
     * @param fileName     真实文件名
     * @return 是否成功
     */
    @Override
    public boolean minecraftModCreate(ToolMinecraftMod minecraftMod, String tempPath, String fileName) {
        if (!tempPath.substring(tempPath.lastIndexOf(".") + 1).equals("jar")) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_CREATE_CATEGORY_FILE_EXTENSION);
        }
        //移动临时文件到常驻文件夹
        fileUtils.copy(fileUploadConfig.getFileTempPath() + tempPath, fileUploadConfig.getMinecraftFilePath() + fileName);
        ToolFile toolFile = this.getCreateToolFile(minecraftMod, fileName);
        toolFile.setPath(fileUploadConfig.getMinecraftFilePath() + fileName);
        toolFile.setCategory(ToolMinecraftModFileEnum.CATEGORY_PERMANENT.getCategory());
        return this.save(toolFile);
    }

    /**
     * 我的世界mod从附件解压并获取狼lang内容
     *
     * @param filePath 常驻附件位置
     * @return 返回内容行
     */
    @Override
    public List<String> minecraftModLangFromFilePath(String filePath) {
        File enUSFile = null;
        File zhCNFile = null;
        List<String> zhCNStringList = null;
        String path = filePath.substring(0, filePath.lastIndexOf("."));
        //解压
        ZipUtil.unpack(
                new File(filePath),
                new File(path)
        );
        log.info("解压完成");
        //查找lang后缀文件名
        List<File> langFileList = fileUtils.searchExtension(new File(path), "lang"
        );
        log.info("查找完成");
        try {
            //读取正确的文件
            //读取文件夹下modId.info文件中的json值modId,匹配改路径下的en_us.lang
            List<MinecraftModFileInfo> minecraftModFileInfo = fileUtils.getMinecraftModFileInfo(path);
            String modId = "";
            if (!minecraftModFileInfo.isEmpty()) {
                modId = "\\" + minecraftModFileInfo.get(0).getModid() + "\\";
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
            return enUSStringList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        fileUtils.copy(fileUploadConfig.getMinecraftFilePath() + path, fileUploadConfig.getMinecraftModFilePath() + path);
        ToolFile toolFile = this.getCreateToolFile(minecraftMod, path);
        toolFile.setPath(fileUploadConfig.getMinecraftFilePath() + path);
        toolFile.setCategory(ToolMinecraftModFileEnum.CATEGORY_GAME.getCategory());
        log.info("移动到游戏目录完成");
        return this.save(toolFile);
    }

    /**
     * 我的世界mod将汉化内容打包到jar
     *
     * @param minecraftModLangList 汉化内容列表
     * @param path                 文件名(不带路径)
     * @return 是否成功
     */
    @Override
    public boolean minecraftModPackageFromLang(List<ToolMinecraftModLang> minecraftModLangList, String path) {
        try {
            File enUSFile = null;
            List<String> zhCNStringList = new ArrayList<>();
            //读取jar文件目录
            String extractFolder = fileUploadConfig.getMinecraftFilePath() + path.substring(0, path.lastIndexOf("."));
            //查找lang后缀文件名
            List<File> langFileList = fileUtils.searchExtension(new File(
                    fileUploadConfig.getMinecraftFilePath() + path.substring(0, path.lastIndexOf("."))), "lang"
            );
            //读取正确的文件->读取文件夹下modId.info文件中的json值modId,匹配改路径下的en_us.lang
            List<MinecraftModFileInfo> minecraftModFileInfo = fileUtils.getMinecraftModFileInfo(extractFolder);
            String modId = "";
            if (!minecraftModFileInfo.isEmpty()) {
                modId = "\\" + minecraftModFileInfo.get(0).getModid() + "\\";
            }
            for (File langFile : langFileList) {
                if (langFile.getName().toLowerCase().equals("en_us.lang") && langFile.getPath().contains(modId)) {
                    enUSFile = langFile;
                }
            }
            if (enUSFile == null) {
                throw new OperationException(ResultCode.MINECRAFT_MOD_CREATE_CATEGORY_FILE_NONE_LANG);
            }
            //读取数据存入list
            for (ToolMinecraftModLang minecraftModLang : minecraftModLangList) {
                zhCNStringList.add(minecraftModLang.getField() + "=" + minecraftModLang.getLang());
            }
            //将汉化内容写入文件
            if (!fileUtils.writerFileStringList(zhCNStringList, enUSFile.getParentFile() + "/zh_cn.lang")) {
                throw new RuntimeException();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //重新打包并移动到常驻文件夹
        ZipUtil.pack(
                new File(fileUploadConfig.getMinecraftFilePath() + path.substring(0, path.lastIndexOf("."))),
                new File(fileUploadConfig.getMinecraftFilePath() + path)
        );
        log.info("重新打包并移动到常驻文件夹完成");
        return true;
    }
}
