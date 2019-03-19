package com.pingan.angel.admin.api.feign;

import com.pingan.angel.admin.api.feign.factory.RemoteUserServiceFallbackFactory;
import com.pingan.angel.admin.api.dto.UserInfo;
import com.pingan.angel.common.core.constant.SecurityConstants;
import com.pingan.angel.common.core.constant.ServiceNameConstants;
import com.pingan.angel.common.core.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(value = ServiceNameConstants.UMPS_SERVICE, fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {
	/**
	 * 通过用户名查询用户、角色信息
	 *
	 * @param username 用户名
	 * @param from     调用标志
	 * @return R
	 */
	@GetMapping("/user/info/{username}")
	Result<UserInfo> info(@PathVariable("username") String username
		, @RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 通过社交账号查询用户、角色信息
	 *
	 * @param inStr appid@code
	 * @return
	 */
	@GetMapping("/social/info/{inStr}")
	Result<UserInfo> social(@PathVariable("inStr") String inStr);
}
