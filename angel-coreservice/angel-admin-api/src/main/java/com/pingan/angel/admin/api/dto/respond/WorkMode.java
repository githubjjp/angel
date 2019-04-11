package com.pingan.angel.admin.api.dto.respond;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Text 工作模式选择指令
 *
 */
@Data
public class WorkMode extends HeadNews{

	/**
	 * d1(服务器发送的d1数据)
	 *  1—进入正常模式
		2—进入工厂测试模式
	 */
	private int type ;
	
	/**
	 *  d2:工作模式选择结果
		0x00—失败
		0xff—成功
	 */
	private boolean isSuccess ;
}
