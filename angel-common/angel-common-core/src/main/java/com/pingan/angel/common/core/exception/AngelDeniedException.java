package com.pingan.angel.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * 403 授权拒绝
 */
@NoArgsConstructor
public class AngelDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AngelDeniedException(String message) {
		super(message);
	}

	public AngelDeniedException(Throwable cause) {
		super(cause);
	}

	public AngelDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public AngelDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
