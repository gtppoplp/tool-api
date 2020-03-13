package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.entity.ToolCommonLog;
import com.gxlirong.tool.mapper.ToolCommonLogMapper;
import com.gxlirong.tool.service.ToolCommonLogService;
import org.springframework.stereotype.Service;

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

    @Override
    public void create(String type, Long Id, String entityName, String log) {
        ToolCommonLog toolCommonLog = new ToolCommonLog();
        toolCommonLog.setType(type);
        toolCommonLog.setTableId(Id);
        toolCommonLog.setEntityName(entityName);
        toolCommonLog.setLog(log);
        this.save(toolCommonLog);
    }
}
