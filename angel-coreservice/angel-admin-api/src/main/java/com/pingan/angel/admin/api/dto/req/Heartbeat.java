package com.pingan.angel.admin.api.dto.req;

import lombok.Data;
/**
 * 
 * @author zhangquan
 * @Text  发送上报数据的时间间隔指令
 */
@Data
public class Heartbeat extends HeadNews {

	/**
	 * d1:1~24(小时)
	 */
	private int hour ;
}
