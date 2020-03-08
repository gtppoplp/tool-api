package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.entity.ToolFile;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author lirong
 * @since 2020-03-07
 */
public interface ToolFileService extends IService<ToolFile> {

    boolean minecraftModCreate(Long id, String path);
}
