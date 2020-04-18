package com.yhzn.web.controller.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import com.yhzn.common.controller.BaseController;
import com.yhzn.common.page.PageUtil;
import com.yhzn.model.security.Permission;
import com.yhzn.model.security.SysRole;
import com.yhzn.model.security.User;
import com.yhzn.service.security.SysRoleService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 角色管理模块
 * 
 * @author liany
 *
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {


	// 角色管理接口
	@Autowired
	private SysRoleService sysRoleService;

	// @Autowired
	// private UserLogsService userLogsService;
	/**
	 * 查询角色信息列表
	 * 
	 * @param request
	 * @return
	 */
	@RequiresPermissions("role:list")
	@RequestMapping(value = "/querySysRoleList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil querySysUserList(HttpServletRequest request) {

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));

		String roleName = request.getParameter("roleName");// 警号

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("roleName", roleName);

		// userLogsService.insertLogByUser((User)
		// request.getSession().getAttribute("user"),
		// "/querySysRoleList", "jsgl", roleName);
		PageBounds bounds = new PageBounds(page, rows);
		List<SysRole> list = sysRoleService.querySysRoleList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<SysRole>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);

		return result;

	}

	/**
	 * 删除角色信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("role:delete")
	@RequestMapping("/deleteSysRole")
	@ResponseBody
	public Map<String, Object> deleteSysRole(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ids = request.getParameter("ids");

		// userLogsService.insertLogByUser((User)
		// request.getSession().getAttribute("user"),
		// "/deleteSysRole", "deleteSysRole", ids);
		String[] idArray = ids.split(",");
		if (null != ids && idArray.length > 0) {
			for (int i = 0; i < idArray.length; i++) {
				sysRoleService.deleteRoleById(idArray[i]);
			}
		}
		map.put("success", true);
		return map;
	}

	/**
	 * 角色新增和编辑页面
	 */
	@RequestMapping("/roleForm")
	public String roleForm(@RequestParam(name = "id", required = false) String id, Model model) {
		if (id != null) {
			SysRole role = sysRoleService.findSysRoleById(id);
			model.addAttribute("role", role);
		}
		return "/sys/sysRole/roleForm";
	}

	/**
	 * 新增用户信息
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequiresPermissions("role:add")
	@RequestMapping(value = "/addSysRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addSysRole(SysRole sysRole, HttpServletRequest request) {
			Map<String,Object> map=new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("user");
		// userLogsService.insertLogByUser((User)
		// request.getSession().getAttribute("user"),
		// "/addSysRole", "addSysRole", sysRole.getRoleName());
		System.out.println(user.getTrueName());
		int flag = sysRoleService.saveSysRole(sysRole, user);
		if (flag > 0) {
			map.put("success", true);
			return map;
		} else {
			map.put("message", "添加失败");
			
			return map;
		}
	}

	/**
	 * 修改角色信息
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequiresPermissions("role:edit")
	@RequestMapping(value = "/editSysRole", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editSysRole(SysRole sysRole, HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String, Object>();
		User user = (User) request.getSession().getAttribute("user");
		// userLogsService.insertLogByUser((User)
		// request.getSession().getAttribute("user"),
		// "/editSysRole", "editSysRole", sysRole.getRoleName());
		int flag = sysRoleService.updateSysRole(sysRole, user);

		if (flag > 0) {
			map.put("success",true);
			return map;
		} else {
			map.put("message", "编辑失败");
			return map;
		}
	}

	/**
	 * 保存角色权限
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("role:add")
	@PostMapping("/rolePermissionsave")
	@ResponseBody
	public JSONObject permissionSave(String roleId, String[] permissionId) {
		JSONObject json=new JSONObject();
		//移除角色原有的权限
		sysRoleService.deletePermissionByRoleId(roleId);
		//添加新的权限
		for (String string : permissionId) {
			
			sysRoleService.createPermission(UUID.randomUUID().toString().replaceAll("-", ""),roleId,string);
		}
		
		json.put("success", true);
		return json;
	}

	/**
	 * 获取角色列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
	@ResponseBody
	public String getRoleList(String roleId, HttpServletRequest request) {
		StringBuilder json = new StringBuilder();
		String str = "";
		List<SysRole> list = sysRoleService.findSysRoleList();
		if (list != null && list.size() != 0) {
			json.append("[");
			for (SysRole sysRole : list) {
				json.append("{\"id\":\"" + sysRole.getId() + "\"");
				json.append(",\"text\":\"" + sysRole.getRoleName() + "\"");
				if (null != roleId && roleId.equals(sysRole.getId())) {
					json.append(",\"checked\":" + true);
				}
				json.append("},");
			}
			str = json.toString();
			str = str.substring(0, str.length() - 1);
			str += "]";
		}
		return str;
	}
	/**
	 * 角色获取已拥有的权限
	 */
	@RequestMapping("/permissionById2")
	@ResponseBody
	public List<Permission> permissionById2(String roleId){
		return sysRoleService.permissionById2(roleId);
		
	}
}
