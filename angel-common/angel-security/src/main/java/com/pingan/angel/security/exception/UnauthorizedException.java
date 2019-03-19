package com.pingan.angel.security.exception;


import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pingan.angel.security.serializer.AngelAuth2ExceptionSerializer;

/**
 * 未被授权
 * @author ouyangenkun
 *
 */
@SuppressWarnings("serial")
@JsonSerialize(using = AngelAuth2ExceptionSerializer.class)
public class UnauthorizedException extends AngelAuth2Exception {

	public UnauthorizedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
