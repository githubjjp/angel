package com.pingan.angel.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pingan.angel.admin.api.dto.MenuTree;
import com.pingan.angel.admin.api.entity.SysMenu;
import com.pingan.angel.admin.api.vo.MenuVO;
import com.pingan.angel.admin.api.vo.TreeUtil;
import com.pingan.angel.admin.service.mysql.service.SysMenuService;
import com.pingan.angel.common.core.constant.CommonConstants;
import com.pingan.angel.common.core.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@RequestMapping("/menu")
@AllArgsConstructor
public class MenuController {
	private final SysMenuService sysMenuService;

	/**
	 * 返回当前用户的树形菜单集合
	 *
	 * @return 当前用户的树形菜单
	 */
	@GetMapping
	public Result getUserMenu() {
		// 获取符合条件的菜单
		Set<MenuVO> all = new HashSet<>();
//		SecurityUtils.getRoles()
//				.forEach(roleId -> all.addAll(sysMenuService.findMenuByRoleId(roleId)));
		List<MenuTree> menuTreeList = all.stream()
				.filter(menuVo -> CommonConstants.MENU.equals(menuVo.getType()))
				.map(MenuTree::new)
				.sorted(Comparator.comparingInt(MenuTree::getSort))
				.collect(Collectors.toList());
		return new Result<>(TreeUtil.bulid(menuTreeList, -1));
	}

	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public Result getTree() {
		return new Result<>(TreeUtil.bulidTree(sysMenuService.list(Wrappers.emptyWrapper()), -1));
	}

	/**
	 * 返回角色的菜单集合
	 *
	 * @param roleId 角色ID
	 * @return 属性集合
	 */
	@GetMapping("/tree/{roleId}")
	public List getRoleTree(@PathVariable Integer roleId) {
		return sysMenuService.findMenuByRoleId(roleId)
				.stream()
				.map(MenuVO::getMenuId)
				.collect(Collectors.toList());
	}

	/**
	 * 通过ID查询菜单的详细信息
	 *
	 * @param id 菜单ID
	 * @return 菜单详细信息
	 */
	@GetMapping("/{id}")
	public Result getById(@PathVariable Integer id) {
		return new Result<>(sysMenuService.getById(id));
	}

	/**
	 * 新增菜单
	 *
	 * @param sysMenu 菜单信息
	 * @return success/false
	 */
	@PostMapping
	public Result save(@Valid @RequestBody SysMenu sysMenu) {
		return new Result<>(sysMenuService.save(sysMenu));
	}

	/**
	 * 删除菜单
	 *
	 * @param id 菜单ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	public Result removeById(@PathVariable Integer id) {
		return sysMenuService.removeMenuById(id);
	}

	/**
	 * 更新菜单
	 *
	 * @param sysMenu
	 * @return
	 */
	@PutMapping
	public Result update(@Valid @RequestBody SysMenu sysMenu) {
		return new Result<>(sysMenuService.updateMenuById(sysMenu));
	}

}
