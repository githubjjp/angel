package com.pingan.angel.admin.api.dto.req;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Text   请求服务器数据指令
 */
@Data
public class RequestServer extends HeadNews {

	/**
	 *  d1 = 1 表示请求服务器更新时间数据，服务器接收之后，发送校时指令，参见第3条的校时指令格式。(设备上电之后会主动请求一次校时指令)
		d1 = 2 表示请求服务器滤芯最大寿命，滤芯剩余寿命，服务器接收之后，发送第14条指令，参见第14条滤芯数据下发指令格式。
	 */
	private int type ;
}
