package com.pingan.angel.admin.api.dto.respond;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Text  
 */
@Data
public class Authentication extends HeadNews {

	/**
	 *  d2: 认证指令执行结果
		0x00—失败
		0xff—成功
	 */
	private boolean isSuccess ;
	
}
