package com.example.demooauthserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/27
 * @Description:
 */
@Slf4j
@Component("userDetailService")
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:{}", username);
        return User.builder().username(username)
                .password(passwordEncoder.encode("123456"))
                .authorities("ROLE_ADMIN")
                .build();
    }
}
