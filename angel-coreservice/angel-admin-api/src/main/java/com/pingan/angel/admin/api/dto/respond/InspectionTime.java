package com.pingan.angel.admin.api.dto.respond;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Text 校时指令
 */
@Data
public class InspectionTime extends HeadNews{

	/**
	 *  d1:校时结果 
		0xff 设置成功
		0x00 设置失败
	 */
	private boolean isSuccess ;
	
}
