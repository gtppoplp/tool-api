package com.gxlirong.tool.utils;

import com.gxlirong.tool.common.api.ResultCode;
import com.gxlirong.tool.common.exception.OperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import static org.apache.catalina.startup.ExpandWar.deleteDir;

/**
 * 文件工具类
 *
 * @author lirong
 */
@Component
@Slf4j
public class FileUtils {

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
     * @param source 原路径
     * @param dest   目标路径
     */
    public void copy(String source, String dest) {
        try {
            //自动创建目录
            File file = new File(dest);
            if (!file.getParentFile().isDirectory()) {
                if (!file.getParentFile().exists()) {
                    if (!file.getParentFile().mkdirs()) {
                        throw new OperationException(ResultCode.FILE_DIRECTORY_CREATE_ERROR);
                    }
                }
            }
            FileChannel inputChannel = new FileInputStream(source).getChannel();
            FileChannel outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
            inputChannel.close();
            outputChannel.close();
        } catch (IOException e) {
            throw new OperationException(ResultCode.FILE_DIRECTORY_CREATE_ERROR, e.getMessage());
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

    /**
     * 删除目录及其文件
     *
     * @param dir 目录
     * @return 是否删除
     */
    public boolean removeDir(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            return false;
        }
        String[] content = file.list();
        assert content != null;
        for (String name : content) {
            File temp = new File(dir, name);
            if (temp.isDirectory()) {
                deleteDir(new File(temp.getAbsolutePath()));
            }
            boolean delete = temp.delete();
        }
        return file.delete();
    }
}
