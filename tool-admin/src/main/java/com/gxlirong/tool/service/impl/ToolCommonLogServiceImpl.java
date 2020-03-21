package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.entity.ToolCommonLog;
import com.gxlirong.tool.mapper.ToolCommonLogMapper;
import com.gxlirong.tool.service.ToolCommonLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author lirong
 * @since 2020-03-11
 */
@Service
public class ToolCommonLogServiceImpl extends ServiceImpl<ToolCommonLogMapper, ToolCommonLog> implements ToolCommonLogService {

    /**
     * 获得日志内容列表
     *
     * @param id         id
     * @param entityName 实体名称
     * @return List<ToolCommonLog>
     */
    @Override
    public List<ToolCommonLog> getList(Long id, String entityName) {
        return this.list(
                new QueryWrapper<ToolCommonLog>()
                        .eq("table_id", id)
                        .eq("entity_name", entityName)
        );
    }

    /**
     * 日志创建
     *
     * @param type       类型
     * @param id         id
     * @param entityName 实体名
     * @param log        日志内容
     */
    @Override
    public void create(String type, Long id, String entityName, String log) {
        ToolCommonLog toolCommonLog = new ToolCommonLog();
        toolCommonLog.setType(type);
        toolCommonLog.setTableId(id);
        toolCommonLog.setEntityName(entityName);
        toolCommonLog.setLog(log);
        this.save(toolCommonLog);
    }
}
