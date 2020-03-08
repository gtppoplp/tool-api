package com.gxlirong.tool.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gxlirong.tool.domain.dto.MinecraftModFileInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 *
 * @author lirong
 */
@Component
@Slf4j
public class FileUtils {
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
     * 在指定目录中查找包含关键字的文件，返回包含指定关键字的文件路径.
     *
     * @param folder  文件目录
     * @param keyword 关键字
     * @return 文件列表
     * @author lirong
     */
    public List<File> searchFiles(File folder, String keyword) {
        List<File> result = new ArrayList<>();
        if (folder.isFile())
            result.add(folder);

        File[] subFolders = folder.listFiles(file -> {
            if (file.isDirectory()) {
                return true;
            }
            return file.getName().toLowerCase().contains(keyword);
        });
        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(file);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }
        return result;
    }

    /**
     * 在指定目录中查找包含关键字的文件，返回包含指定关键字的文件路径.
     *
     * @param folder  文件目录
     * @param keyword 关键字
     * @return 文件列表
     * @author lirong
     */
    public List<File> searchExtension(File folder, String keyword) {
        List<File> result = new ArrayList<>();
        if (folder.isFile())
            result.add(folder);
        /*查找后缀名*/
        File[] subFolders = folder.listFiles(file -> {
            if (file.isDirectory()) {
                return true;
            }
            return file.getName().toLowerCase().endsWith(keyword);
        });

        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(file);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }
        return result;
    }

    /**
     * 复制文件
     *
     * @param srcPathStr 原路径
     * @param desPathStr 目标路径
     */
    public void copy(String srcPathStr, String desPathStr) {
        try {
            //创建输入流对象
            FileInputStream fis = new FileInputStream(srcPathStr);
            //创建输出流对象
            FileOutputStream fos = new FileOutputStream(desPathStr);
            //创建搬运工具
            byte[] data = new byte[1024 * 8];
            //创建长度
            int len;
            //循环读取数据
            while ((len = fis.read(data)) != -1) {
                fos.write(data, 0, len);
            }
            //释放资源
            fis.close();
            //释放资源
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得文件字符串内容列表
     *
     * @return List<String>
     */
    public List<String> readerFileStringList(String path) throws IOException {
        if (path == null) {
            return null;
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        List<String> stringList = new ArrayList<>();
        String readLineString;
        while ((readLineString = bufferedReader.readLine()) != null) {
            if (!readLineString.equals("")) {
                stringList.add(readLineString);
            }
        }
        bufferedReader.close();
        return stringList;
    }

    /**
     * 将列表内容写入文件
     *
     * @return List<String>
     */
    public boolean writerFileStringList(List<String> stringList, String path) throws IOException {
        if (stringList == null) {
            return false;
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
        for (String string : stringList) {
            bufferedWriter.write(string);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        return true;
    }
}
