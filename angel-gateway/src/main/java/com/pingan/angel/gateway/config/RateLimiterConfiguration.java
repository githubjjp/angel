package com.pingan.angel.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

/**
 * 路由限流配置
 */
@Configuration
public class RateLimiterConfiguration {
	
	@Bean(name = IpAddressKeyResolver.BEAN_NAME)
    public KeyResolver ipAddressKeyResolver() {
        return new IpAddressKeyResolver();
    }
}
