package com.pingan.angel.admin.api.dto;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Date 2019-3-20 16:24:40
 * @text 设备在离线查询
 */
@Data
public class IsOnline {

	/**
	 * 设备的sn号
	 */
	private String sn ;
	
	/**
	 * 是否在线
	 */
	private boolean onlineState ;
	
	/**
	 * 离线过期时间 (时间戳)
	 */
	private long onlineExpire ;
	
}
