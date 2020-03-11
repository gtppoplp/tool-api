package com.gxlirong.tool.service.impl;

import com.gxlirong.tool.entity.ToolLog;
import com.gxlirong.tool.mapper.ToolLogMapper;
import com.gxlirong.tool.service.IToolLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ToolLogServiceImpl extends ServiceImpl<ToolLogMapper, ToolLog> implements IToolLogService {

}
