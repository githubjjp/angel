package com.pingan.angel.admin.api.dto;

import com.pingan.angel.admin.api.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends SysUser {
	/**
	 * 角色ID
	 */
	private List<Integer> role;

	private Integer deptId;

	/**
	 * 新密码
	 */
	private String newpassword1;
}
