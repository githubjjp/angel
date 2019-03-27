package com.pingan.angel.admin.api.dto;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Date 2019-3-20 16:33:17
 * @Text 滤芯信息
 *
 */
@Data
public class FilterInfo {

	/**
	 * 滤芯位置号
	 */
	private int index ;
	
	/**
	 * 滤芯名称
	 */
	private String name ;
	
	/**
	 * 剩余寿命(单位:小时)
	 */
	private int life ;
	
	/**
	 * 滤芯总寿命（单位：小时）
	 */
	private int lifeAll ;
}
