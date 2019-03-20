package com.pingan.angel.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pingan.angel.security.component.AngelAuth2ExceptionSerializer;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@JsonSerialize(using = AngelAuth2ExceptionSerializer.class)
@SuppressWarnings("serial")
public class InvalidException extends AngelAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
