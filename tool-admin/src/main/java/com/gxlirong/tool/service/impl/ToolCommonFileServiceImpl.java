package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.properties.FileUploadProperties;
import com.gxlirong.tool.domain.dto.MinecraftModFileInfo;
import com.gxlirong.tool.entity.ToolCommonFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;
import com.gxlirong.tool.entity.ToolMinecraftModLang;
import com.gxlirong.tool.enums.ToolMinecraftModFileEnum;
import com.gxlirong.tool.mapper.ToolCommonFileMapper;
import com.gxlirong.tool.service.ToolCommonFileService;
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
public class ToolCommonFileServiceImpl extends ServiceImpl<ToolCommonFileMapper, ToolCommonFile> implements ToolCommonFileService {
    @Autowired
    private FileUploadProperties fileUploadProperties;
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private UserUtils userUtils;

    /**
     * 获得我的世界模组配置列表
     *
     * @param path 路径
     * @return 我的世界模组配置列表
     */
    public List<MinecraftModFileInfo> getMinecraftModFileInfo(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        return objectMapper.readValue(new File(path + "/mcmod.info"), new TypeReference<List<MinecraftModFileInfo>>() {
        });
    }


    /**
     * 获得文件实体
     *
     * @param minecraftMod ToolMinecraftMod
     * @param path         文件名(不带路径)
     * @return 文件实体
     */
    private ToolCommonFile getCreateToolFile(ToolMinecraftMod minecraftMod, String path) {
        ToolCommonFile toolCommonFile = new ToolCommonFile();
        toolCommonFile.setName(path.substring(0, path.lastIndexOf(".")));
        toolCommonFile.setExtension(path.substring(path.lastIndexOf(".") + 1));
        toolCommonFile.setTableId(minecraftMod.getId());
        toolCommonFile.setEntityName(minecraftMod.getClass().getSimpleName());
        userUtils.insertBefore(toolCommonFile);
        return toolCommonFile;
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
        fileUtils.copy(fileUploadProperties.getFileTempPath() + tempPath, fileUploadProperties.getMinecraftFilePath() + fileName);
        ToolCommonFile toolCommonFile = this.getCreateToolFile(minecraftMod, fileName);
        toolCommonFile.setPath(fileUploadProperties.getMinecraftFilePath() + fileName);
        toolCommonFile.setCategory(ToolMinecraftModFileEnum.CATEGORY_PERMANENT.getCategory());
        return this.save(toolCommonFile);
    }

    /**
     * 我的世界解压mod
     *
     * @param filePath 常驻附件位置
     * @return 返回解压好的路径
     */
    @Override
    public String decompressionZip(String filePath) {
        String path = filePath.substring(0, filePath.lastIndexOf("."));
        ZipUtil.unpack(new File(filePath), new File(path));
        return path;
    }

    /**
     * 我的世界mod从附件解压并获取狼lang内容
     *
     * @param filePath     常驻附件位置
     * @param langFileName lang文件名
     * @return 返回内容行
     */
    @Override
    public List<String> minecraftModLangFromFilePath(String filePath, String langFileName) {
        //查找lang后缀文件名
        List<File> langFileList = fileUtils.searchExtension(new File(filePath), "lang");
        try {
            //读取正确的文件
            //读取文件夹下modId.info文件中的json值modId,匹配改路径下的en_us.lang
            List<MinecraftModFileInfo> minecraftModFileInfo = this.getMinecraftModFileInfo(filePath);
            String modId = "";
            if (!minecraftModFileInfo.isEmpty()) {
                modId = "\\" + minecraftModFileInfo.get(0).getModid() + "\\";
            }
            for (File langFile : langFileList) {
                if (langFile.getName().toLowerCase().equals(langFileName) && langFile.getPath().contains(modId)) {
                    //读取数据存入list
                    return fileUtils.readerFileStringList(langFile.getPath());
                }
            }
            return null;
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
        fileUtils.copy(fileUploadProperties.getMinecraftFilePath() + path, fileUploadProperties.getMinecraftModFilePath() + path);
        ToolCommonFile toolCommonFile = this.getCreateToolFile(minecraftMod, path);
        toolCommonFile.setPath(fileUploadProperties.getMinecraftFilePath() + path);
        toolCommonFile.setCategory(ToolMinecraftModFileEnum.CATEGORY_GAME.getCategory());
        log.info("移动到游戏目录完成");
        return this.save(toolCommonFile);
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
            String extractFolder = fileUploadProperties.getMinecraftFilePath() + path.substring(0, path.lastIndexOf("."));
            //查找lang后缀文件名
            List<File> langFileList = fileUtils.searchExtension(new File(
                    fileUploadProperties.getMinecraftFilePath() + path.substring(0, path.lastIndexOf("."))), "lang"
            );
            //读取正确的文件->读取文件夹下modId.info文件中的json值modId,匹配改路径下的en_us.lang
            List<MinecraftModFileInfo> minecraftModFileInfo = this.getMinecraftModFileInfo(extractFolder);
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
                new File(fileUploadProperties.getMinecraftFilePath() + path.substring(0, path.lastIndexOf("."))),
                new File(fileUploadProperties.getMinecraftFilePath() + path)
        );
        log.info("重新打包并移动到常驻文件夹完成");
        return true;
    }
}
