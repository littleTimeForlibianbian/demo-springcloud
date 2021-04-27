package com.example.demooauthserver.config.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/27
 * @Description:
 */
/*@Configuration
//标识我是认证服务器
@EnableAuthorizationServer*/
public class MemoryAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    //注入身份认证管理类
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * 客户端详情的配置
     * 配置解析，授权服务器指定客户端能访问授权服务器
     * 为第三方应用办法客户端id为clientId test  密码为test的 支持的授权类型为密码模式（一共有四种模式）
     * 颁发的令牌的有效期为1小时
     * 通过该令牌可以访问那些资源（可以配置多个资源服务器）
     * 访问资源服务器的read  write权限
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()//模拟配置，基于内存，正式使用基于数据库 jdbc()
                .withClient("test")
                .secret(passwordEncoder.encode("test"))
                //支持的模式：密码模式，授权码模式
                .authorizedGrantTypes("password", "authorization_code")
                //颁发的令牌是读权限还是写权限
                .scopes("read")
                //token的有效期
                .accessTokenValiditySeconds(3600)
                //能访问那些资源服务器
                .resourceIds("order-provider", "product-provider")
                //配置了授权码模式以后，配置的回调地址
                .redirectUris("http://www.baidu.com")
            .and()
                .withClient("order_app")
                .secret(passwordEncoder.encode("test"))
                .authorizedGrantTypes("password")
                .scopes("read")
                .refreshTokenValiditySeconds(1800)
                .resourceIds("order-provider")
            .and()
                .withClient("product_app")
                .secret(passwordEncoder.encode("test"))
                .authorizedGrantTypes("password")
                .scopes("read")
                .accessTokenValiditySeconds(1800)
                .resourceIds("product-app");
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //表示客户端访问认证中心的时候需要带上token令牌
        security.checkTokenAccess("isAuthenticated()");
    }

    /**
     * 验证用户相关信息
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //认证服务器委托一个authenticationManager来验证我们的用户信息
        endpoints.authenticationManager(authenticationManager);
    }
}
