package com.pingan.angel.admin.api.dto.req;

import lombok.Data;
import com.pingan.angel.admin.api.dto.respond.HeadNews;
/**
 * 
 * @author zhangquan
 * @Text 认证指令
 */
@Data
public class Authentication extends HeadNews{

	/**
	 *  d1:数据类型
		0x01 第1个滤芯认证
		0x02 第2个滤芯认证
		0x03 第3个滤芯认证
		0x04 第4个滤芯认证
		0x05 第5个滤芯认证
		0x06 发送开机码(大写的字符串形式)
	 */
	private int filter ;
	
	/**
	 * d2:第1个滤芯小时
	 */
	private double filterHour ;
	
	/**
	 * d3:第1个滤芯流量
	 */
	private double filterFlow ;
	
	/**
	 * d2:开机码(字符串，20位)
	 */
	private String startingCode ;
}
