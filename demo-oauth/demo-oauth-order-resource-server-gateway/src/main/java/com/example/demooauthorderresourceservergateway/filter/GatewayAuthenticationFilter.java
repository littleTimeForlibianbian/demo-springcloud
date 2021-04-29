package com.example.demooauthorderresourceservergateway.filter;

import com.example.demooauthorderresourceservergateway.TokenInfo;
import com.example.demooauthorderresourceservergateway.exception_my.MyGatewayException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: Bruce Lee
 * @Date: 2021/4/28
 * @Description: 认证过滤器，主要是用来完成身份的认证
 */
@Slf4j
@Component
public class GatewayAuthenticationFilter implements GlobalFilter, Ordered, InitializingBean {

    @Autowired
    private RestTemplate restTemplate;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String rawPath = exchange.getRequest().getURI().getRawPath();
        log.info("rawPath:{}", rawPath);
        //非检查路径
        if (shouldSkipUrl(rawPath)) {
            log.info("should skip url :{}", rawPath);
            chain.filter(exchange);
        }
        //获取请求头中的token
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            throw new MyGatewayException("未携带token参数");
        }

        //获取校验token
        TokenInfo tokenInfo;
        try {
            tokenInfo = getTokenInfo(authorization);
        } catch (Exception e) {
            log.info("token 校验异常：{}", e.getLocalizedMessage());
            throw new MyGatewayException("token 校验异常");
        }
        //获取到tokenInfo信息，根据一定的校验规则，判断校验成功还是失败
        log.info(tokenInfo.toString());
        System.out.println(tokenInfo.toString());
        //将tokenInfo的信息保存到exchange中，去下一个过滤器中去验证
        //重新build一个serverWebExchange
        ServerHttpRequest httpRequest = exchange.getRequest().mutate().header("userName", tokenInfo.getUserName()).build();
        ServerWebExchange build = exchange.mutate().request(httpRequest).build();
        build.getAttributes().put("tokenInfo", tokenInfo);
        return chain.filter(build);
    }

    private TokenInfo getTokenInfo(String tokenAll) {
        String realToken = StringUtils.substringAfter(tokenAll, "bearer ");
        String url = "http://auth-server/oauth/check_token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //gateway的client_id和secret的配置，不用实现什么web相关的接口了
        headers.setBasicAuth("gateway_app", "test");
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("token", realToken);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity(param, headers);
        ResponseEntity<TokenInfo> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TokenInfo.class);
        return exchange.getBody();
    }

    private boolean shouldSkipUrl(String url) {
        return false;
    }


    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
