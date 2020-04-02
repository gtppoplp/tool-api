package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.entity.ToolMinecraftModLang;
import com.gxlirong.tool.properties.FileUploadProperties;
import com.gxlirong.tool.domain.dto.MinecraftModFileInfo;
import com.gxlirong.tool.entity.ToolCommonFile;
import com.gxlirong.tool.entity.ToolMinecraftMod;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    @Override
    public ToolCommonFile findByToolMinecraftModPermanent(ToolMinecraftMod minecraftMod) {
        ToolCommonFile file = this.getOne(
                new QueryWrapper<ToolCommonFile>()
                        .eq("table_id", minecraftMod.getId())
                        .eq("entity_name", minecraftMod.getClass().getSimpleName())
                        .eq("category", ToolMinecraftModFileEnum.CATEGORY_PERMANENT.getCategory())
                        .eq("is_deleted", false)
        );
        if (file == null) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_FILE_NONE);
        }
        return file;
    }

    /**
     * 遍历节点获得指定key值
     * 如果是列表,则用逗号隔开返回值
     *
     * @param node 节点
     * @param key  key
     * @return string
     */
    public String jsonLeaf(JsonNode node, String key) {
        //如果是值
        if (node.isValueNode() && key == null) {
            return node.toString();
        }
        //如果是对象
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> it = node.fields();
            StringBuilder string = new StringBuilder();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (entry.getKey().equals(key)) {
                    string.append(jsonLeaf(entry.getValue(), null));
                } else {
                    string.append(jsonLeaf(entry.getValue(), key));
                }
            }
            return string.toString().replaceAll("\"(\\w+)\"", "$1");
        }
        //如果是数组则输出
        if (node.isArray()) {
            StringBuilder string = new StringBuilder();
            for (JsonNode jsonNode : node) {
                String s = jsonLeaf(jsonNode, key);
                if (s != null && !s.equals("")) {
                    string.append(s).append(",");
                }
            }
            if (string.length() != 0) {
                return string.substring(0, string.length() - 1);
            }
        }
        return "";
    }

    /**
     * 获得我的世界模组配置列表
     *
     * @param path 路径
     * @return 我的世界模组配置列表
     */
    public MinecraftModFileInfo getMinecraftModFileInfo(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode root = objectMapper.readTree(new File(path + "/mcmod.info"));
        MinecraftModFileInfo minecraftModFileInfo = new MinecraftModFileInfo();
        minecraftModFileInfo.setModid(this.jsonLeaf(root, "modid"));
        minecraftModFileInfo.setName(this.jsonLeaf(root, "name"));
        minecraftModFileInfo.setDescription(this.jsonLeaf(root, "description"));
        minecraftModFileInfo.setVersion(this.jsonLeaf(root, "version"));
        minecraftModFileInfo.setMcversion(this.jsonLeaf(root, "mcversion"));
        minecraftModFileInfo.setUrl(this.jsonLeaf(root, "url"));
        minecraftModFileInfo.setAuthorList(this.jsonLeaf(root, "authorList"));
        minecraftModFileInfo.setDependencies(this.jsonLeaf(root, "dependencies"));
        return minecraftModFileInfo;
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
            throw new OperationException(ResultCode.MINECRAFT_MOD_FILE_EXTENSION);
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
     * 我的世界mod从附件中读取lang路径
     *
     * @param filePath     常驻附件位置
     * @param langFileName lang文件名
     * @return 返回内容行
     */
    private String getLangPath(String filePath, String langFileName) {
        //查找lang后缀文件名
        List<File> langFileList = fileUtils.searchExtension(new File(filePath), "lang");
        try {
            //读取正确的文件
            //读取文件夹下modId.info文件中的json值modId,匹配改路径下的en_us.lang
            MinecraftModFileInfo minecraftModFileInfo = this.getMinecraftModFileInfo(filePath);
            String modId = "";
            if (minecraftModFileInfo != null) {
                modId = "\\" + minecraftModFileInfo.getModid() + "\\";
            }
            for (File langFile : langFileList) {
                if (langFile.getName().toLowerCase().equals(langFileName) && langFile.getPath().contains(modId)) {
                    //读取数据存入list
                    return langFile.getPath();
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 我的世界mod从附件解压并获取狼lang内容
     *
     * @param filePath     常驻附件位置
     * @param langFileName lang文件名
     * @return 返回内容行
     */
    @Override
    public List<String> minecraftModLangFromFilePath(String filePath, String langFileName) throws IOException {
        return fileUtils.readerFileStringList(this.getLangPath(filePath, langFileName));
    }

    /**
     * 我的世界mod将汉化内容写入到汉化文件
     *
     * @param notChineseList notChineseList
     * @param filePathName   文件夹名称
     */
    @Override
    public void minecraftModPackageEnLangFromChineseList(List<ToolMinecraftModLang> notChineseList, String filePathName) {
        try {
            String langPath = this.getLangPath(filePathName, "en_us.lang");
            if (langPath == null) {
                throw new RuntimeException();
            }
            langPath = langPath.replace("en_us.lang", "zh_cn.lang");
            List<String> zhCNStringList = new ArrayList<>();
            //读取数据存入list
            for (ToolMinecraftModLang minecraftModLang : notChineseList) {
                zhCNStringList.add(minecraftModLang.getField() + "=" + minecraftModLang.getLang());
            }
            //将汉化内容写入文件
            if (!fileUtils.writerFileStringList(zhCNStringList, langPath)) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * @param path 文件名(完整路径,包括后缀)
     */
    @Override
    public void minecraftModPackageFromPath(String path) {
        //重新打包并移动到常驻文件夹
        ZipUtil.pack(
                new File(path.substring(0, path.lastIndexOf("."))),
                new File(path)
        );
        log.info("重新打包并移动到常驻文件夹完成");
    }
}
