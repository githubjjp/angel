package com.pingan.angel.gateway.filter;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.pingan.angel.common.core.constant.SecurityConstants;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 密码解密工具类
 */
@SuppressWarnings("rawtypes")
@Slf4j
@Component
public class PasswordDecoderFilter extends AbstractGatewayFilterFactory {
    private static final String PASSWORD = "password";
    private static final String KEY_ALGORITHM = "AES";
    @Value("${security.encode.key:1234567812345678}")
    private String encodeKey;

    private static String decryptAES(String data, String pass) {
        AES aes = new AES(Mode.CBC, Padding.NoPadding,
                new SecretKeySpec(pass.getBytes(), KEY_ALGORITHM),
                new IvParameterSpec(pass.getBytes()));
        return new String(aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
    }

    private static String ecryptAES(String data, String pass) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding,
                new SecretKeySpec(pass.getBytes(), KEY_ALGORITHM),
                new IvParameterSpec(pass.getBytes()));
        return new String(Base64.encode(aes.encrypt(data)).getBytes(), StandardCharsets.UTF_8);
    }

 /*   public static void main(String[] args) {
        System.out.println(decryptAES(ecryptAES("123456", "thanks,pig4cloud"), "thanks,pig4cloud"));
    }*/


    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(request.getURI().getPath(), SecurityConstants.OAUTH_TOKEN_URL)) {
                return chain.filter(exchange);
            }

            URI uri = exchange.getRequest().getURI();
            String queryParam = uri.getRawQuery();
            Map<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, CharsetUtil.UTF_8);

            String password = paramMap.get(PASSWORD);
            if (StrUtil.isNotBlank(password)) {
                try {
                    password = decryptAES(password, encodeKey);
                } catch (Exception e) {
                    //log.error("密码解密失败:{}", password);
                    return Mono.error(e);
                }
                paramMap.put(PASSWORD, password.trim());
            }

            URI newUri = UriComponentsBuilder.fromUri(uri)
                    .replaceQuery(HttpUtil.toParams(paramMap))
                    .build(true)
                    .toUri();

            ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(newRequest).build());
        };
    }
}
