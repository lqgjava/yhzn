package com.yhzn.service.security;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.Permission;
import com.yhzn.model.security.SysModule;
import com.yhzn.model.security.SysRole;
import com.yhzn.model.security.SysRolePermis;
import com.yhzn.model.security.User;

public interface SysRoleService {

	/**
	 * 查询角色信息列表
	 * 
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	List<SysRole> querySysRoleList(PageBounds bounds, Map<String, Object> parameter);

	/**
	 * 删除角色信息
	 * 
	 * @param id
	 */
	public void deleteRoleById(String id);

	/**
	 * 保存角色信息
	 * 
	 * @param sysRole
	 * @param user
	 * @return
	 */
	public int saveSysRole(SysRole sysRole, User user);

	/**
	 * 更新角色信息
	 * 
	 * @param sysRole
	 * @param user
	 * @return
	 */
	public int updateSysRole(SysRole sysRole, User user);

	/**
	 * 查询角色信息
	 * 
	 * @param id
	 * @return
	 */
	public SysRole findSysRoleById(String id);

	/**
	 * 获取模块根结点列表
	 * 
	 * @return
	 */
	public List<SysModule> findModuleTree(String id);

	/**
	 * 获取子节点列表
	 * 
	 * @param id
	 * @return
	 */
	public List<SysModule> findNodesById(Map<String, String> map);

	/**
	 * 保存权限
	 * 
	 * @param roleId
	 * @param moduleIds
	 */
	public void savePermis(String roleId, String moduleIds);

	/**
	 * 获取角色列表
	 */
	public List<SysRole> findSysRoleList();

	/**
	 * 查询用户权限列表
	 * 
	 * @param id
	 * @return
	 */
	public List<SysRolePermis> queryUserPermisListById(String id);

	/**
	 * 根据角色删除角色拥有的权限
	 * 
	 * @param roleId
	 */
	void deletePermissionByRoleId(String roleId);

	/**
	 * 给角色赋权限
	 * 
	 * @param roleId
	 * @param permissionId
	 */
	void createPermission(String id,String roleId, String permissionId);

	List<Permission> permissionById(String roleId);
	String findRolesByUserName(String userName);
	List<Permission> permissionById2(String roleId);

	void deleteRolePermission(String id);
}
