package com.pingan.angel.security.exception;


import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pingan.angel.security.serializer.AngelAuth2ExceptionSerializer;

/**
 * 请求中指定的方法不被允许
 * @author ouyangenkun
 *
 */
@SuppressWarnings("serial")
@JsonSerialize(using = AngelAuth2ExceptionSerializer.class)
public class MethodNotAllowed extends AngelAuth2Exception {

	public MethodNotAllowed(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
