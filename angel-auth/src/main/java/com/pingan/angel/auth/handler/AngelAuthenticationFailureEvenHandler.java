package com.pingan.angel.auth.handler;

import com.pingan.angel.security.handler.AuthenticationFailureEvenHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
public class AngelAuthenticationFailureEvenHandler extends AuthenticationFailureEvenHandler {

	/**
	 * 处理登录失败方法
	 * <p>
	 *
	 * @param authenticationException 登录的authentication 对象
	 * @param authentication          登录的authenticationException 对象
	 */
	@Override
	public void handle(AuthenticationException authenticationException, Authentication authentication) {
		authenticationException.printStackTrace();
		log.info("用户：{} 登录失败，异常：{}"+authentication.toString(), authentication.getPrincipal(), authenticationException.getLocalizedMessage());
		authenticationException.getStackTrace();
		
	}
}
