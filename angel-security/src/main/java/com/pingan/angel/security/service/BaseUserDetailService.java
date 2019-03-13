package com.pingan.angel.security.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pingan.angel.admin.constants.CodeConstants;
import com.pingan.angel.admin.dto.LoginUser;
import com.pingan.angel.admin.dto.UserInfo;
import com.pingan.angel.admin.entity.SysUser;
import com.pingan.angel.admin.util.R;
import com.pingan.angel.security.constants.SecurityConstants;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义用户认证Service
 * @author ouyangenkun
 *
 */
@Service
@AllArgsConstructor
public class BaseUserDetailService implements UserDetailsService {

	private final CacheManager cacheManager;
    
    /**
	 * 用户密码登录
	 *
	 * @param username 用户名
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cache cache = cacheManager.getCache("user_details");
		if (cache != null && cache.get(username) != null) {
			return (LoginUser) cache.get(username).get();
		}

		//R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
		UserInfo user = new UserInfo();
		SysUser sysuser = new SysUser();
		sysuser.setUserId(1);
		sysuser.setUsername("admin");
		sysuser.setPassword("$2a$10$RpFJjxYiXdEsAGnWp/8fsOetMuOON96Ntk/Ym2M/RKRyU0GZseaDC");
		sysuser.setPhone("17034642999");
		sysuser.setLockFlag("0");
		
		String[] permissions = new String[]{"ROLE_ADMIN","ROLE_CQQ"};
		Integer[] roles = new Integer[]{1,2};
		
		user.setSysUser(sysuser);
		user.setPermissions(permissions);
		user.setRoles(roles);
		
		R<UserInfo> result = new R<UserInfo>();
		result.setData(user);
		
		UserDetails userDetails = getUserDetails(result);
		cache.put(username, userDetails);
		return userDetails;
	}

	/**
	 * 构建userdetails
	 *
	 * @param result 用户信息
	 * @return
	 */
	private UserDetails getUserDetails(R<UserInfo> result) {
		if (result == null || result.getData() == null) {
			throw new UsernameNotFoundException("用户不存在");
		}

		UserInfo info = result.getData();
		Set<String> dbAuthsSet = new HashSet<>();
		if (ArrayUtil.isNotEmpty(info.getRoles())) {
			// 获取角色
			Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add(SecurityConstants.ROLE + role));
			// 获取资源
			dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

		}
		Collection<? extends GrantedAuthority> authorities
			= AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		SysUser user = info.getSysUser();

		// 构造security用户
		return new LoginUser(user.getUserId(), user.getUsername(), SecurityConstants.BCRYPT + user.getPassword(),
			StrUtil.equals(user.getLockFlag(), CodeConstants.STATUS_NORMAL), true, true, true, authorities);
	}
}
