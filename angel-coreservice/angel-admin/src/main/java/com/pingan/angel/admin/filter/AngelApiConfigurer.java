package com.pingan.angel.admin.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AngelApiConfigurer implements WebMvcConfigurer {

    @Bean   //把拦截器注入为bean
    public HandlerInterceptor getMyInterceptor() {
        return new AngelApiFilter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor()).addPathPatterns();
    }
}
