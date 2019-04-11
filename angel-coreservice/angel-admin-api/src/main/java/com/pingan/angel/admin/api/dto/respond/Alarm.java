package com.pingan.angel.admin.api.dto.respond;

import java.util.Date;
import lombok.Data;

/**
 * 
 * @author zhangquan
 * @Text 告警指令
 */
@Data
public class Alarm extends HeadNews{

	/**
	 *  故障代码(2bytes)：
		0x0000: 无故障
		0x0001: 加热NTC故障
		0x0002：预热NTC故障
		0x0004：温水NTC故障
		0x0008：冷水NTC故障
		0x0010：溢水故障
		0x0020: 低水位故障
		0x0040: 漏水故障
		0x0080：连续制水故障(取消)
		0x0100：进水电磁阀故障
		0x0200：出水电磁阀故障
		0x0400：冲洗电磁阀故障
	 */
	private int fault ;
	
	/**
	 *  保护代码(2bytes)：
		0x0000: 无保护
		0x0001：补水电磁阀保护
		0x0002：连续制水工作保护
		0x0004：干烧保护
		0x0008：滤芯寿命到期
		0x0010：制冷反馈信号线异常(Y1251)
		0x0020：保留
		0x0040：保留
		0x0080：保留
	 */
	private int protect ;
	
	/**
	 * 告警时间
	 */
	private Date time ;
}
