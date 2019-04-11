package com.pingan.angel.admin.api.dto.req;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Text 消息头
 */
@Data
public class HeadNews {

	/** 
	 * 标识
	 */
	private int flag ;
	
	/**
	 * 版本
	 */
	private int version ;
	
	/**
	 * 命令
	 */
	private int cmd ;
	
	/**
	 * GPRS状态信息
	 */
	private int gprs ;
	
	/**
	 * 经纬度信息
	 */
	private String addr ;
	
	/**
	 * 配件码
	 */
	private String barcodeid ;
	
}
