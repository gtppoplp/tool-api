package com.gxlirong.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxlirong.tool.entity.ToolRbacResourceEntity;
import com.gxlirong.tool.entity.ToolRbacUserEntity;
import com.gxlirong.tool.mapper.ToolRbacUserMapper;
import com.gxlirong.tool.service.ToolRbacUserService;
import com.gxlirong.tool.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lirong
 * @since 2020-02-29
 */
@Service
@Slf4j
public class ToolRbacUserServiceImpl extends ServiceImpl<ToolRbacUserMapper, ToolRbacUserEntity> implements ToolRbacUserService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
//TODO 登录记录            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public ToolRbacUserEntity getUserByUsername(String username) {
        return this.getOne(
                new QueryWrapper<ToolRbacUserEntity>()
                        .eq("username", username)
                        .eq("is_deleted", false)
        );
    }

    @Override
    public List<ToolRbacResourceEntity> getPermissionList(Long userId) {
        ArrayList<ToolRbacResourceEntity> toolRbacResourceEntities = new ArrayList<>();
        ToolRbacResourceEntity toolRbacResourceEntity = new ToolRbacResourceEntity();
        toolRbacResourceEntities.add(toolRbacResourceEntity);
        return toolRbacResourceEntities;
    }
}
