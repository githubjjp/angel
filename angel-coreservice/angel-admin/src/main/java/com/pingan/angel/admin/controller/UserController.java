
package com.pingan.angel.admin.controller;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pingan.angel.admin.api.dto.UserDTO;
import com.pingan.angel.admin.api.entity.SysConfig;
import com.pingan.angel.admin.api.entity.SysUser;
import com.pingan.angel.admin.mogodb.service.SysConfigService;
import com.pingan.angel.admin.mysql.service.SysUserService;
import com.pingan.angel.common.core.util.Result;
import com.pingan.angel.security.annotation.Inner;
import com.pingan.angel.security.util.SecurityUtils;

import lombok.AllArgsConstructor;

/**
 * @date 2019/2/1
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
	private  SysUserService userService;
	
	private  SysConfigService sysConfigService;
	/**
	 * 获取当前用户全部信息
	 *
	 * @return 用户信息
	 */
	@GetMapping(value = {"/info"})
	public Result info() {
		String username = SecurityUtils.getUser().getUsername();
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new Result<>(Boolean.FALSE, "获取当前用户信息失败");
		}
		return new Result<>(userService.findUserInfo(user));
	}

	/**
	 * 获取指定用户全部信息
	 *
	 * @return 用户信息
	 */
	@Inner
	@GetMapping("/info/{username}")
	public Result info(@PathVariable String username) {
		SysUser user = userService.getOne(Wrappers.<SysUser>query()
				.lambda().eq(SysUser::getUsername, username));
		if (user == null) {
			return new Result<>(Boolean.FALSE, String.format("用户信息为空 %s", username));
		}
		return new Result<>(userService.findUserInfo(user));
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id ID
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	public Result user(@PathVariable Integer id) {
		return new Result<>(userService.selectUserVoById(id));
	}

	/**
	 * 根据用户名查询用户信息
	 *
	 * @param username 用户名
	 * @return
	 */
	@GetMapping("/details/{username}")
	public Result user(@PathVariable String username) {
		SysUser condition = new SysUser();
		condition.setUsername(username);
		return new Result<>(userService.getOne(new QueryWrapper<>(condition)));
	}

	/**
	 * 删除用户信息
	 *
	 * @param id ID
	 * @return R
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_user_del')")
	public Result userDel(@PathVariable Integer id) {
		SysUser sysUser = userService.getById(id);
		return new Result<>(userService.deleteUserById(sysUser));
	}

	/**
	 * 添加用户
	 *
	 * @param userDto 用户信息
	 * @return success/false
	 */
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_user_add')")
	public Result user(@RequestBody UserDTO userDto) {
		return new Result<>(userService.saveUser(userDto));
	}

	/**
	 * 更新用户信息
	 *
	 * @param userDto 用户信息
	 * @return R
	 */
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_user_edit')")
	public Result updateUser(@Valid @RequestBody UserDTO userDto) {
		return new Result<>(userService.updateUser(userDto));
	}

	/**
	 * 分页查询用户
	 *
	 * @param page    参数集
	 * @param userDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("/page")
	public Result getUserPage(Page page, UserDTO userDTO) {
		return new Result<>(userService.getUsersWithRolePage(page, userDTO));
	}

	/**
	 * 修改个人信息
	 *
	 * @param userDto userDto
	 * @return success/false
	 */
	@PutMapping("/edit")
	public Result updateUserInfo(@Valid @RequestBody UserDTO userDto) {
		return userService.updateUserInfo(userDto);
	}

	/**
	 * @param username 用户名称
	 * @return 上级部门用户列表
	 */
	@GetMapping("/ancestor/{username}")
	public Result listAncestorUsers(@PathVariable String username) {
		return new Result<>(userService.listAncestorUsers(username));
	}

	
	
	@GetMapping("/log/{id}")
	public Result getlog(@PathVariable String id) {
		return new Result<>(sysConfigService.getOne(Wrappers.<SysConfig>query()
				.lambda().eq(SysConfig::getVariable, id)));
	}

}
