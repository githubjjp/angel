package com.pingan.angel.admin.controller;

import com.pingan.angel.admin.api.entity.SysDept;
import com.pingan.angel.admin.service.mysql.service.SysDeptService;
import com.pingan.angel.common.core.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dept")
public class DeptController {
	private final SysDeptService sysDeptService;

	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return SysDept
	 */
	@GetMapping("/{id}")
	public Result getById(@PathVariable Integer id) {
		return new Result<>(sysDeptService.getById(id));
	}


	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public Result getTree() {
		return new Result<>(sysDeptService.selectTree());
	}

	/**
	 * 返回当前用户树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/user-tree")
	public Result getUserTree() {
		return new Result<>(sysDeptService.getUserTree());
	}

	/**
	 * 添加
	 *
	 * @param sysDept 实体
	 * @return success/false
	 */
	@PostMapping
	public Result save(@Valid @RequestBody SysDept sysDept) {
		return new Result<>(sysDeptService.saveDept(sysDept));
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	public Result removeById(@PathVariable Integer id) {
		return new Result<>(sysDeptService.removeDeptById(id));
	}

	/**
	 * 编辑
	 *
	 * @param sysDept 实体
	 * @return success/false
	 */
	@PutMapping
	public Result update(@Valid @RequestBody SysDept sysDept) {
		sysDept.setUpdateTime(LocalDateTime.now());
		return new Result<>(sysDeptService.updateDeptById(sysDept));
	}
}
