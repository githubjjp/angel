package com.pingan.angel.admin.api.feign;

import com.pingan.angel.admin.api.entity.SysLog;
import com.pingan.angel.admin.api.feign.factory.RemoteLogServiceFallbackFactory;
import com.pingan.angel.common.core.constant.SecurityConstants;
import com.pingan.angel.common.core.constant.ServiceNameConstants;
import com.pingan.angel.common.core.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 */
@FeignClient(value = ServiceNameConstants.UMPS_SERVICE, fallbackFactory = RemoteLogServiceFallbackFactory.class)
public interface RemoteLogService {
	/**
	 * 保存日志
	 *
	 * @param sysLog 日志实体
	 * @param from   内部调用标志
	 * @return succes、false
	 */
	@PostMapping("/log")
	Result<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader(SecurityConstants.FROM) String from);
}
