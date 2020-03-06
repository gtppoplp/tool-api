package com.gxlirong.tool.util;

import com.gxlirong.tool.domain.bo.ToolRbacUserDetails;
import com.gxlirong.tool.entity.BaseUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 用户工具类
 *
 * @author lirong
 */
@Component
public class UserUtil {

    /**
     * 获取当前用户
     *
     * @return Authentication
     */
    public ToolRbacUserDetails getUser() {
        return (ToolRbacUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 插入时初始化的用户信息
     *
     * @author lirong
     */
    public void insertBefore(BaseUser examples) {
        ToolRbacUserDetails user = getUser();
        examples.setCreatedId(user.getUserId());
        examples.setCreatedTime(LocalDateTime.now());
        examples.setOrganizationId(user.getOrganizationId());
        examples.setDepartmentId(user.getDepartmentId());
        examples.setDomain(user.getDomain());
    }

    /**
     * 更新时初始化的用户信息
     *
     * @author lirong
     */
    public void updateBefore(BaseUser examples) {
        ToolRbacUserDetails user = getUser();
        examples.setUpdatedId(user.getUserId());
        examples.setUpdatedTime(LocalDateTime.now());
    }

    /**
     * 删除时初始化的用户信息
     *
     * @author lirong
     */
    public void deleteBefore(BaseUser examples) {
        ToolRbacUserDetails user = getUser();
        examples.setDeletedId(user.getUserId());
        examples.setDeletedTime(LocalDateTime.now());
    }
}
