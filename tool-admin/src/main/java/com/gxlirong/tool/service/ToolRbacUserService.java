package com.gxlirong.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxlirong.tool.entity.ToolRbacResourceEntity;
import com.gxlirong.tool.entity.ToolRbacUserEntity;

import java.util.List;

/**
 * <p>
 * 后台管理员Service
 * </p>
 *
 * @author lirong
 * @since 2020-02-29
 */
public interface ToolRbacUserService extends IService<ToolRbacUserEntity> {
    /**
     * 根据用户名获取后台管理员
     */
    ToolRbacUserEntity getAdminByUsername(String username);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<ToolRbacResourceEntity> getPermissionList(Long adminId);
}
