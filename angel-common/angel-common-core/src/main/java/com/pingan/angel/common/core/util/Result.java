package com.pingan.angel.common.core.util;

import java.io.Serializable;

import com.pingan.angel.common.core.constant.CommonConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


/**
 * 响应信息主体
 *
 * @param <T>
 * @author lengleng
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class Result<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private int code = CommonConstants.SUCCESS;

	@Getter
	@Setter
	private String msg = "success";


	@Getter
	@Setter
	private T data;

	public Result() {
		super();
	}

	public Result(T data) {
		super();
		this.data = data;
	}

	public Result(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}

	public Result(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = CommonConstants.FAIL;
	}
}
