package com.pingan.angel.admin.api.dto.respond;

import lombok.Data;

/**
 * 解析消息
 * @author zhangquan
 *
 * @param <T>
 */
@Data
public class ReqContent<T extends HeadNews> {

	/**
	 * 状态码
	 */
	private int code ;
	
	/**
	 * 状态信息
	 */
	private String msg ;
	
	/**
	 * 指令编码
	 */
	private String cmd ;
	
	private T a ;
	
}
