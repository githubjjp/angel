package com.pingan.angel.admin.dto;

import java.io.Serializable;

import com.pingan.angel.admin.entity.SysUser;

import lombok.Data;


@SuppressWarnings("serial")
@Data
public class UserInfo implements Serializable {
	/**
	 * 用户基本信息
	 */
	private SysUser sysUser;
	/**
	 * 权限标识集合
	 */
	private String[] permissions;

	/**
	 * 角色集合
	 */
	private Integer[] roles;
}
