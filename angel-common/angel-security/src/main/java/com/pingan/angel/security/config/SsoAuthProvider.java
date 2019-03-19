package com.pingan.angel.security.config;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class SsoAuthProvider implements AuthenticationProvider{
 
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("自定义provider调用");
        //// 返回一个Token对象表示登陆成功
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), Collections.<GrantedAuthority>emptyList());
    }
 
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
