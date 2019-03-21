package com.pingan.angel.admin.api.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pingan.angel.admin.api.feign.factory.RemoteTokenServiceFallbackFactory;
import com.pingan.angel.common.core.constant.SecurityConstants;
import com.pingan.angel.common.core.constant.ServiceNameConstants;
import com.pingan.angel.common.core.util.Result;


@FeignClient(value = ServiceNameConstants.AUTH_SERVICE, fallbackFactory = RemoteTokenServiceFallbackFactory.class)
public interface RemoteTokenService {
	/**
	 * 分页查询token 信息
	 *
	 * @param params 分页参数
	 * @param from   内部调用标志
	 * @return page
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/oauth/listToken")
	Result selectPage(@RequestBody Map<String, Object> params, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 删除token
	 *
	 * @param token token
	 * @param from  调用标志
	 * @return
	 */
	@DeleteMapping("/oauth/delToken/{token}")
	Result<Boolean> deleteTokenById(@PathVariable("token") String token, @RequestHeader(SecurityConstants.FROM) String from);
}
