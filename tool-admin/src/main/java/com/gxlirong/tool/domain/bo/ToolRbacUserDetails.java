package com.gxlirong.tool.domain.bo;

import com.gxlirong.tool.entity.ToolRbacResourceEntity;
import com.gxlirong.tool.entity.ToolRbacUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 *
 * @author lirong
 */
public class ToolRbacUserDetails implements UserDetails {
    private ToolRbacUserEntity umsAdmin;
    private List<ToolRbacResourceEntity> permissionList;

    public ToolRbacUserDetails(ToolRbacUserEntity umsAdmin, List<ToolRbacResourceEntity> permissionList) {
        this.umsAdmin = umsAdmin;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getUrl() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getUrl()))
                .collect(Collectors.toList());
    }

    public Integer getDomain() {
        return umsAdmin.getDomain();
    }

    public Long getDepartmentId() {
        return umsAdmin.getDepartmentId();
    }

    public Long getOrganizationId() {
        return umsAdmin.getOrganizationId();
    }

    public Long getUserId() {
        return umsAdmin.getUserId();
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getIsEnabled();
    }
}
