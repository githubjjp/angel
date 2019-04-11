package com.pingan.angel.admin.api.dto.respond;

/**
 * 
 * @author zhangquan
 * @Text  发送上报数据的时间间隔指令
 */
public class Heartbeat extends HeadNews {

	/**
	 *  d1:
		0x00—失败
		0xff—成功
	 */
	private boolean isSuccess ;
}
