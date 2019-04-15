package com.pingan.angel.admin.api.dto.req;

import lombok.Data;
import com.pingan.angel.admin.api.dto.respond.HeadNews;
/**
 * 
 * @author zhangquan
 * @Text   工作模式选择指令
 *
 */
@Data
public class WorkMode extends HeadNews {

	/**
	 *  d1:
		1—进入正常模式
		2—进入工厂测试模式
	 */
	private int type ;
}
