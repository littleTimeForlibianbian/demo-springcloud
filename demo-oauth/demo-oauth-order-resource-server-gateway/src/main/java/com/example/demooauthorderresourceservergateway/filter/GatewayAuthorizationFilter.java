package com.example.demooauthorderresourceservergateway.filter;

import com.example.demooauthorderresourceservergateway.TokenInfo;
import com.example.demooauthorderresourceservergateway.exception_my.MyGatewayException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/29
 * @Description: 鉴权过滤器。order必须在认证过滤器的后面
 * 从 token中取出用户所有的权限，判断是否具有当前路径的请求权限
 */
@Slf4j
@Component
public class GatewayAuthorizationFilter implements GlobalFilter, Ordered {
    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getPath().toString();
        if (shouldSkipUrl(url)) {
            log.info("should skip url :{}", url);
        }
        //获取tokenInfo对象
        TokenInfo tokenInfo = (TokenInfo) exchange.getAttributes().get("tokenInfo");
        if (!tokenInfo.isActive()) {
            throw new MyGatewayException("token 已经过期！");
        }
        List<String> authorities = tokenInfo.getAuthorities();
        boolean hasAuthority = false;
        for (String authority : authorities) {
            if (url.contains(authority)) {
                hasAuthority = true;
                break;
            }
        }
        if (!hasAuthority) {
            throw new MyGatewayException("对不起，您没有访问权限");
        }
        //继续调用其余的过滤器
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

    /**
     * 是否跳过url认证的判断方法
     *
     * @param url 当前请求中的url
     * @return
     */
    private boolean shouldSkipUrl(String url) {
        return false;
    }
}
