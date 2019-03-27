package com.pingan.angel.admin.api.constants;

/**
 * 
 * @author zhangquan
 * @Date 2019-3-19 09:32:22
 * @text 接口常量
 */
public class APIConstant {
	
	/**
	 * 设备激活
	 */
	public static String[] ACTIVE = {"sn"} ;
	
	/**
	 *设备基本信息查询
	 */
	public static String[] DEVICE_INFO = {"sn"} ;
	
	/**
	 * 设备在离线查询
	 */
	public static String[] IS_ONLINE = {"sn"} ;

	/**
	 * 设备控制
	 */
	public static String[] CMD = {"sn","cmd"} ;

	/**
	 * 设备实时信息查询
	 */
	public static String[] STATUS = {"sn"} ;
	
	/**
	 * 滤芯重置
	 */
	public static String[] RESET_FILTER = {"sn","code","index"} ;
	
	/**
	 * 设备用水量查询
	 */
	public static String[] USE_WATER_INFO = {"sn","date"} ;
	
	/**
	 * MD5加密key
	 */
	public static String MD5_KEY = "123456qwerty" ;
	
}
