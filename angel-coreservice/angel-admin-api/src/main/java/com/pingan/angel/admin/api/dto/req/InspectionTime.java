package com.pingan.angel.admin.api.dto.req;

import com.pingan.angel.admin.api.dto.respond.HeadNews;
import lombok.Data;

import java.util.Date;

/**
 * 
 * @author zhangquan
 * @Text 校时指令
 */
@Data
public class InspectionTime extends HeadNews {

	/**
	 * 校验时间
	 */
	private Date time ;
}
