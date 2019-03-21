package com.pingan.angel.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pingan.angel.security.component.AngelAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author lengleng
 * @date 2019/2/1
 * 自定义OAuth2Exception
 */
@SuppressWarnings("serial")
@JsonSerialize(using = AngelAuth2ExceptionSerializer.class)
public class AngelAuth2Exception extends OAuth2Exception {
	@Getter
	private String errorCode;

	public AngelAuth2Exception(String msg) {
		super(msg);
	}

	public AngelAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
