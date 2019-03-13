package com.pingan.angel.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.pingan.angel.admin.security.util.HttpUtils;
import com.pingan.angel.admin.util.R;

import lombok.AllArgsConstructor;

/**
 * @author lengleng
 * 授权拒绝处理器，覆盖默认的OAuth2AccessDeniedHandler
 * 包装失败信息到PigDeniedException
 */
@Component
@AllArgsConstructor
public class AngelAccessFailureHandler implements AuthenticationFailureHandler{
	 @Override
	    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
	        HttpUtils.writerError(new R<Object>(401,exception.getMessage()),response);
	    }

}
