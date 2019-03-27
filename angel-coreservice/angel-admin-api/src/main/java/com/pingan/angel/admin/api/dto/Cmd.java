package com.pingan.angel.admin.api.dto;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Date 2019-3-20 16:27:18
 * @Text 设备控制
 */
@Data
public class Cmd {

	/**
	 * 设备的sn号
	 */
	private String sn ;
	
	/**
	 * 控制编码
	 * 	0-关机
		1-开机
		2-冲洗
		3-锁机
		4-解锁
	 */
	private int cmd ;
	
}
