package com.dahua.tech.easywork.gateway.service.impl;

import com.dahua.tech.easywork.core.utils.SHAEncrypt;
import com.dahua.tech.easywork.gateway.model.CustomGrantedAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * @auther jarod.jin 2018/12/3
 */
@Slf4j
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = SHAEncrypt.getMessageDigest(authentication.getCredentials().toString());
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        // 认证逻辑
        if (name.equals(userDetails.getUsername()) && password.equals(userDetails.getPassword())) {
            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            for(GrantedAuthority ga : userDetails.getAuthorities()){
                authorities.add(ga);
            }
            // 生成令牌
            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        }else {
            log.info(authentication.getName()+":密码错误~");
            throw new BadCredentialsException("密码错误~");
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
