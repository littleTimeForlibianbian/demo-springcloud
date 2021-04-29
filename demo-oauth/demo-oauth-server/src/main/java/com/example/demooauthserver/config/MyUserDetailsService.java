package com.example.demooauthserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/27
 * @Description: 身份验证/加载工具类，
 */
@Slf4j
@Component("userDetailService")
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 基于RBAC的角色权限模型加载到该username所对应的所有权限 加载到UserDetail中
     * 网关鉴权的功能就是检查用户访问的路径是否在此处加载的权限范围内，如果在范围内，则鉴权通过
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //正常情况下应该是从数据库获取用户的角色 权限 等信息赋值，此处只是作为测试使用，
        log.info("username:{}", username);
        return User.builder().username(username)
                .password(passwordEncoder.encode("123456"))
                .authorities(new SimpleGrantedAuthority("/**"))
                .authorities("ROLE_ADMIN")
                .build();
    }
}
