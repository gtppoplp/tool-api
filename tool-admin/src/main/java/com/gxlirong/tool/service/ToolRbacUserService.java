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
 */
public interface ToolRbacUserService extends IService<ToolRbacUserEntity> {
    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     * @author lirong
     */
    String login(String username, String password);

    /**
     * 根据用户名获取后台管理员
     *
     * @param username 用户名
     * @author lirong
     */
    ToolRbacUserEntity getUserByUsername(String username);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     *
     * @param userId 用户名
     * @author lirong
     */
    List<ToolRbacResourceEntity> getPermissionList(Long userId);
}
