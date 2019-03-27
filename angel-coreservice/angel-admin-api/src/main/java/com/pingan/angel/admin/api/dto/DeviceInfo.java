package com.pingan.angel.admin.api.dto;

import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Date 2019-3-20 16:12:39
 * @Text 设备基本信息
 */
@Data
public class DeviceInfo {

	/**
	 * 设备的sn号
	 */
	private String sn ;
	
	/**
	 * 产品名称
	 */
	private String productName ;
	
	/**
	 * 故障代码
	 * 
		0	无故障
		1	加热NTC故障
		2	预热NTC故障
		4	温水NTC故障
		8	冷水NTC故障
		16	溢水故障
		32	低水位故障
		64	漏水故障
		128	连续制水故障
		256	进水电磁阀故障
		512	出水电磁阀故障
		1024	冲洗电磁阀故障
	 */
	private int warnCode ;
	
	/**
	 * 保护代码
	 * 
		0	无故障
		1	补水电磁阀保护
		2	连续制水工作保护
		4	干烧保护
		8	滤芯寿命到期
	 */
	private int protectCode ;
	
	
}
