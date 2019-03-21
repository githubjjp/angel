package com.pingan.angel.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pingan.angel.security.component.AngelAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@SuppressWarnings("serial")
@JsonSerialize(using = AngelAuth2ExceptionSerializer.class)
public class ForbiddenException extends AngelAuth2Exception {

	public ForbiddenException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "access_denied";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.FORBIDDEN.value();
	}

}

