package com.pingan.angel.common.core.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pingan.angel.common.core.util.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lengleng
 * @date 2019/2/1
 * 全局的的异常处理器
 */
@Controller
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 全局异常.
	 *
	 * @param e the e
	 * @return R
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Result exception(Exception e) {
		log.error("全局异常信息 ex={}", e.getMessage(), e);
		return new Result<>(e);
	}

	/**
	 * validation Exception
	 *
	 * @param exception
	 * @return R
	 */
	@SuppressWarnings("rawtypes")
	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Result bodyValidExceptionHandler(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		Result result = new Result();
		result.setMsg(fieldErrors.get(0).getDefaultMessage());
		log.warn(fieldErrors.get(0).getDefaultMessage());
		return result;
	}

}
