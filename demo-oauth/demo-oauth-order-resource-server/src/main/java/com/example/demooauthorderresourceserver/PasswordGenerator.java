package com.example.demooauthorderresourceserver;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/28
 * @Description:
 */
public class PasswordGenerator {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("test"));
    }
}
