package com.pingan.angel.admin.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pingan.angel.admin.api.entity.SysLog;
import com.pingan.angel.admin.api.vo.PreLogVo;
import com.pingan.angel.admin.mysql.service.SysLogService;
import com.pingan.angel.common.core.util.Result;

import lombok.AllArgsConstructor;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/log")
public class LogController {
	private final SysLogService sysLogService;

	/**
	 * 简单分页查询
	 *
	 * @param page   分页对象
	 * @param sysLog 系统日志
	 * @return
	 */
	@GetMapping("/page")
	public Result getLogPage(Page page, SysLog sysLog) {
		return new Result<>(sysLogService.page(page, Wrappers.query(sysLog)));
	}

	/**
	 * 删除日志
	 *
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	public Result removeById(@PathVariable Long id) {
		return new Result<>(sysLogService.removeById(id));
	}

	/**
	 * 插入日志
	 *
	 * @param sysLog 日志实体
	 * @return success/false
	 */
	@PostMapping
	public Result save(@Valid @RequestBody SysLog sysLog) {
		return new Result<>(sysLogService.save(sysLog));
	}

	/**
	 * 批量插入前端异常日志
	 *
	 * @param preLogVoList 日志实体
	 * @return success/false
	 */
	@PostMapping("/logs")
	public Result saveBatchLogs(@RequestBody List<PreLogVo> preLogVoList) {
		return new Result<>(sysLogService.saveBatchLogs(preLogVoList));
	}
}
