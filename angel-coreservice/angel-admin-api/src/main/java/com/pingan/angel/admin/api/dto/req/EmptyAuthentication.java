package com.pingan.angel.admin.api.dto.req;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Text  清除认证状态指令
 */
@Data
public class EmptyAuthentication extends HeadNews{

	/**
	 *  d1:
		0xff 表示全部认证数据清除
	 */
	private int emptySum ;
	
}
