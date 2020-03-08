package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import com.gxlirong.tool.config.FileUploadConfig;
import com.gxlirong.tool.domain.dto.ChineseTranslate;
import com.gxlirong.tool.entity.ToolFile;
import com.gxlirong.tool.mapper.ToolFileMapper;
import com.gxlirong.tool.service.ToolFileService;
import com.gxlirong.tool.utils.ChineseUtils;
import com.gxlirong.tool.utils.FileUtils;
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

    @Override
    public boolean minecraftModCreate(Long id, String path) {
        if (!path.substring(path.lastIndexOf(".") + 1).equals("jar")) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_CREATE_CATEGORY_FILE_EXTENSION);
        }
        //解压
        ZipUtil.unpack(
                new File(fileUploadConfig.getFileTempPath() + path),
                new File(fileUploadConfig.getFileTempPath() + path.substring(0, path.lastIndexOf(".")))
        );
        List<File> langFileList = fileUtils.searchExtension(new File(
                fileUploadConfig.getFileTempPath() + path.substring(0, path.lastIndexOf("."))), "lang"
        );
        //读取正确的文件
        File enUSFile = null;
        File zhCNFile = null;
        for (File langFile : langFileList) {
            if (langFile.getName().toLowerCase().equals("en_us.lang")) {
                enUSFile = langFile;
            }
            if (langFile.getName().toLowerCase().equals("zh_cn.lang")) {
                zhCNFile = langFile;
            }
        }
        if (enUSFile == null) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_CREATE_CATEGORY_FILE_NONE_LANG);
        }
        if (zhCNFile == null) {
            fileUtils.copy(enUSFile.getPath(), enUSFile.getParentFile() + "/zh_cn.lang");
            zhCNFile = new File(enUSFile.getParentFile() + "/zh_cn.lang");
        }
        //读取内容并汉化
        try {
            BufferedReader enUSFileReader = new BufferedReader(new FileReader(enUSFile.getPath()));
            BufferedWriter enUSFileWriter = new BufferedWriter(new FileWriter(zhCNFile.getPath()));
            String readLineString;
            //全部机翻
            while ((readLineString = enUSFileReader.readLine()) != null) {
                //包含=号则汉化单词
                if (readLineString.contains("=")) {
                    //插入空格后汉化
                    ChineseTranslate chineseString = chineseUtils.getChineseString(chineseUtils.stringInsertWhitespace(readLineString.substring(readLineString.lastIndexOf("=") + 1)));
                    readLineString = readLineString.substring(0, readLineString.lastIndexOf("=") + 1) + chineseString.getTranslateResult().get(0).get(0).getTgt();
                    //去空格
                    readLineString = readLineString.replace(" ", "");
                    //标点符号转小写
                    readLineString = chineseUtils.toDBC(readLineString);
                }
                log.info(readLineString);
                enUSFileWriter.write(readLineString);
                enUSFileWriter.newLine();//写入换行符
            }
            enUSFileWriter.close();
            enUSFileReader.close();
        } catch (Exception e) {
            throw new OperationException(ResultCode.MINECRAFT_MOD_CREATE_CATEGORY_FILE_ERROR);
        }
        //TODO 重新打包
//        ZipUtil.pack(
//                new File(fileUploadConfig.getFileTempPath() + path.substring(0, path.lastIndexOf("."))),
//                new File(fileUploadConfig.getFileTempPath() + path)
//        );
        return false;
    }
}
