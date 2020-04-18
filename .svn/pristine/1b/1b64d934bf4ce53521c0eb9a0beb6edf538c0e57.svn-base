package com.yhzn.web.controller.security;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.yhzn.common.controller.BaseController;
import com.yhzn.common.util.CoreConst;
import com.yhzn.model.security.Permission;
import com.yhzn.service.impl.security.ShiroService;
import com.yhzn.service.security.PermissionService;
import com.yhzn.service.security.SysRoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private ShiroService shiroService;
	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 新增权限页面和编辑页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/addmodule")
	public String addmodulePage(@RequestParam(name = "id", required = false) String id, Model model) {
		if (id != null) {
			Permission permission = permissionService.findById(id);
			model.addAttribute("per", permission);
		}
		return "/sys/sysModule/moduledetail";
	}

	/* 权限列表数据 */
	@RequiresPermissions("permission:list")
	@PostMapping("/list")
	@ResponseBody
	public List<Permission> list() {
		// 获取菜单信息
		List<Permission> permsList = permissionService.selectAllMenu();
		// 根据菜单找区域信息
		for (Permission pre : permsList) {
			pre.setText(pre.getName());
			List<Permission> areas = permissionService.selectAreaByParent(pre.getId());
			for (Permission area : areas) {
				area.setText(area.getName());	
				//获取按钮权限信息
				List<Permission> selectanniu = permissionService.selectButtonByParent(area.getId());
				for (Permission permission : selectanniu) {
					permission.setText(permission.getName());
				}
				area.setChildren(selectanniu);
			}			
			pre.setChildren(areas);
		}
		return permsList;

	}

	/**
	 * 获取菜单区域权限
	 * 
	 */
	@RequestMapping("/menu")
	@ResponseBody
	public List<Permission> combocList() {
		// 获取菜单信息
		List<Permission> permsList = permissionService.selectAllMenu();
		// 根据菜单找区域信息
		for (Permission pre : permsList) {
			pre.setText(pre.getName());
			System.out.println(pre.getId());
			List<Permission> areas = permissionService.selectAreaByParent(pre.getId());
			for (Permission area : areas) {
				area.setText(area.getName());
			}
			pre.setChildren(areas);
		}
		return permsList;

	}

	/* 添加权限 */
	@RequiresPermissions("permission:add")
	@ResponseBody
	@PostMapping("/add")
	public Map<String, Object> addPermission(Permission permission) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int a = permissionService.insert(permission);
			if (a > 0) {
				shiroService.updatePermission();
				map.put("success", true);
				return map;
			} else {
				map.put("scucess", false);
				return map;
			}
		} catch (Exception e) {
			logger.error(String.format("PermissionController.addPermission%s", e));
			throw e;
		}
	}

	/* 删除权限 */
	@RequiresPermissions("permission:delete")
	@ResponseBody
	@PostMapping("/delete")
	public Map<String, Object> deletePermission(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 查找下级资源权限数量
			int subPermsByPermissionIdCount = permissionService.selectSubPermsByParentId(id);
			if (subPermsByPermissionIdCount > 0) {
				map.put("error", "改资源存在下级资源，无法删除！");
				return map;
			}
			//删除角色拥有的资源
			sysRoleService.deleteRolePermission(id);
			int a = permissionService.updateStatus(id, CoreConst.STATUS_INVALID);
			if (a > 0) {
				shiroService.updatePermission();
				map.put("success", true);
				return map;
			} else {
				map.put("error", "删除权限失败");
				return map;
			}
		} catch (Exception e) {
			logger.error(String.format("PermissionController.deletePermission%s", e));
			throw e;
		}
	}

	/* 编辑权限 */
	@RequiresPermissions("permission:edit")
	@ResponseBody
	@PostMapping("/edit")
	public Map<String, Object> editPermission(@ModelAttribute("permission") Permission permission) {
		System.out.println(permission);
		Map<String, Object> map = new HashMap<String, Object>();
		if (permission.getStatus() == null) {
			permission.setStatus(2);
		}
		int a = permissionService.updateById(permission);
		if (a > 0) {
			shiroService.updatePermission();
			map.put("success", "编辑权限成功");
			return map;
		} else {
			map.put("error", "编辑权限失败");
			return map;
		}
	}
	/**
	 * 获取菜单区域权限
	 * 
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<Permission> tree() {
		// 获取菜单信息
		List<Permission> permsList = permissionService.selectAllMenu();
		// 根据菜单找区域信息
		for (Permission pre : permsList) {
			pre.setText(pre.getName());
			List<Permission> areas = permissionService.selectAreaByParent(pre.getId());
			for (Permission area : areas) {
				area.setText(area.getName());	
				//获取按钮权限信息
				List<Permission> selectanniu = permissionService.selectButtonByParent(area.getId());
				for (Permission permission : selectanniu) {
					permission.setText(permission.getName());
				}
				area.setChildren(selectanniu);
			}			
			pre.setChildren(areas);
		}
		return permsList;

	}
	
}
