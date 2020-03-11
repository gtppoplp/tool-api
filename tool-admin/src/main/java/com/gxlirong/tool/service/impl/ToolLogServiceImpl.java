package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.entity.ToolLog;
import com.gxlirong.tool.mapper.ToolLogMapper;
import com.gxlirong.tool.service.ToolLogService;
import com.gxlirong.tool.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ToolLogServiceImpl extends ServiceImpl<ToolLogMapper, ToolLog> implements ToolLogService {

    @Override
    public void create(String type, Long Id, String entityName, String log) {
        ToolLog toolLog = new ToolLog();
        toolLog.setType(type);
        toolLog.setTableId(Id);
        toolLog.setEntityName(entityName);
        toolLog.setLog(log);
        this.save(toolLog);
    }
}
