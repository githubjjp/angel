package com.pingan.angel.admin.api.dto.req;

import lombok.Data;

/**
 * 转译消息
 * @author zhangquan
 *
 * @param <T>
 */
@Data
public class ReqContent<T extends HeadNews> {
	
	/**
	 * 指令编码
	 */
	private String cmd ;
	
	private T a ;
	
}
