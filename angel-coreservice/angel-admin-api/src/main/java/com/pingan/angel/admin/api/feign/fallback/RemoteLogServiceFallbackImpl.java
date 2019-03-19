package com.pingan.angel.admin.api.feign.fallback;

import org.springframework.stereotype.Component;

import com.pingan.angel.admin.api.entity.SysLog;
import com.pingan.angel.admin.api.feign.RemoteLogService;
import com.pingan.angel.common.core.util.Result;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
public class RemoteLogServiceFallbackImpl implements RemoteLogService {
	@Setter
	private Throwable cause;

	/**
	 * 保存日志
	 *
	 * @param sysLog 日志实体
	 * @param from   内部调用标志
	 * @return succes、false
	 */
	@Override
	public Result<Boolean> saveLog(SysLog sysLog, String from) {
		log.error("feign 插入日志失败", cause);
		return null;
	}
}
