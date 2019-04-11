package com.pingan.angel.admin.api.dto.req;

import lombok.Data;

import com.pingan.angel.admin.api.dto.respond.HeadNews;

/**
 * 
 * @author zhangquan
 * @Text 设备控制指令
 */
@Data
public class EquipmentControl extends HeadNews{

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
	/**
		0x10:预留
		0x20:预留
		0x40:预留
		0x80:预留
	 */
	
}
