package com.pingan.angel.admin.api.dto.respond;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Text 设备控制指令格式
 */
@Data
public class EquipmentControl extends HeadNews {

	//D1
	/**
	 * 0x01:冲洗
	 */
	private boolean rinse ;
	
	/**
	 * 0x02:开机
	 */
	private boolean starts ;
	
	/**
	 * 0x04:关机
	 */
	private boolean shutdown ;
	
	/**
	 * 0x08:锁机
	 */
	private boolean lockMachine ;
	
	//D2
	/**
	 * 控制指令
	 * 0xff--成功
	 * 0x00--失败
	 */
	private boolean isSuccess ;
	
}
