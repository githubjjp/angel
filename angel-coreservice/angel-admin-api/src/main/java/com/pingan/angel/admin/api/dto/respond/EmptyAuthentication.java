package com.pingan.angel.admin.api.dto.respond;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Text 清除认证状态指令
 */
@Data
public class EmptyAuthentication extends HeadNews{

	/**
	 *  d1(服务器发送的d1数据)
	 *  d1:
		0xff 表示全部认证数据清除
	 */
	private int emptySum ;
	
	/**
	 *  d2:清除结果
		0x00—失 败
		0xff—成功
	 */
	private boolean isSuccess ;
	
}
