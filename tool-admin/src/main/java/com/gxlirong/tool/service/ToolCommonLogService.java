package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.entity.ToolCommonLog;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author lirong
 * @since 2020-03-11
 */
public interface ToolCommonLogService extends IService<ToolCommonLog> {

    void create(String type, Long minecraftModId, String entityName, String log);
}
