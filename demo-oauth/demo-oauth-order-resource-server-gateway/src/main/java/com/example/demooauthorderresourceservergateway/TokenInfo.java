package com.example.demooauthorderresourceservergateway;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/28
 * @Description:
 */
@Data
@ToString
public class TokenInfo {
    private List<String> aud;
    private String userName;
    private List<String> scope;
    private boolean active;
    private long exp;
    private List<String> authorities;
    private String clientId;
}
