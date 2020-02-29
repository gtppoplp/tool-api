package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.entity.ToolRbacResourceEntity;
import com.gxlirong.tool.entity.ToolRbacUserEntity;
import com.gxlirong.tool.mapper.ToolRbacUserMapper;
import com.gxlirong.tool.service.ToolRbacUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lirong
 * @since 2020-02-29
 */
@Service
public class ToolRbacUserServiceImpl extends ServiceImpl<ToolRbacUserMapper, ToolRbacUserEntity> implements ToolRbacUserService {

    @Override
    public ToolRbacUserEntity getAdminByUsername(String username) {
        return null;
    }

    @Override
    public List<ToolRbacResourceEntity> getPermissionList(Long adminId) {
        return null;
    }
}
