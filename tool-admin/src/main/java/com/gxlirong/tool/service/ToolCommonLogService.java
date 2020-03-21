package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.entity.ToolCommonLog;

import java.util.List;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author lirong
 * @since 2020-03-11
 */
public interface ToolCommonLogService extends IService<ToolCommonLog> {

    /**
     * 获得日志内容列表
     *
     * @param id         id
     * @param entityName 实体名称
     * @return List<ToolCommonLog>
     */
    List<ToolCommonLog> getList(Long id, String entityName);

    /**
     * 日志创建
     *
     * @param type       类型
     * @param id         id
     * @param entityName 实体名
     * @param log        日志内容
     */
    void create(String type, Long id, String entityName, String log);
}
