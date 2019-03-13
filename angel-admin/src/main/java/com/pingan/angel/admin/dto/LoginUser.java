//package com.pingan.angel.admin.dto;
//
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import com.pingan.angel.admin.entity.SysUser;
//
//import lombok.Getter;
//
//public class LoginUser extends User{
//
//	/**
//	 * 用户ID
//	 */
//	@Getter
//	private Integer id;
//	
//	public LoginUser(Integer id, String username, String password, boolean enabled, boolean accountNonExpired,
//			boolean credentialsNonExpired, boolean accountNonLocked,
//			Collection<? extends GrantedAuthority> authorities) {
//		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//		this.id = id;
//	}
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//}
