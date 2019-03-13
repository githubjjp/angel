package com.pingan.angel.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pingan.angel.security.serializer.AngelAuth2ExceptionSerializer;

/**
 * 无效请求
 * @author ouyangenkun
 *
 */
@SuppressWarnings("serial")
@JsonSerialize(using = AngelAuth2ExceptionSerializer.class)
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
