package com.pingan.angel.admin.api.dto;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Date 2019-3-20 16:36:25
 * @Text 设备用水量
 */
@Data
public class UseWaterInfo {

	/**
	 * 设备的sn号
	 */
	private String sn ;
	
	/**
	 * 用水总量(单位：升)
	 */
	private int flow ;
	
	/**
	 * 纯水总量（单位：升）
	 */
	private int flowNet ;
}
