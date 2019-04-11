package com.pingan.angel.admin.api.dto.respond;

import lombok.Data;

/**
 * 
 * @author zhangquan	
 * @Text   请求SIM卡的CCID指令
 */
@Data
public class RequestSim extends HeadNews {

	/**
	 * sim 
	 */
	private String ccid ;
}
