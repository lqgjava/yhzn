package com.yhzn.dao.security;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.Permission;
import com.yhzn.model.security.SysModule;
import com.yhzn.model.security.SysRole;
import com.yhzn.model.security.SysRolePermis;

public interface SysRoleDao {

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
	 * @return
	 */
	public int saveSysRole(SysRole sysRole);

	/**
	 * 更新角色信息
	 * 
	 * @param sysRole
	 * @return
	 */
	public int updateSysRole(SysRole sysRole);

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
	 * 删除权限
	 * 
	 * @return
	 */
	public void deleteSysRolePermisById(String id);

	/**
	 * 新增权限
	 * 
	 * @return
	 */
	public void saveSysRolePermis(SysRolePermis sysRolePermis);

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
	 * 根据角色id删除权限
	 * 
	 * @param roleId
	 */
	void deletePermissionByRoleId(String roleId);

	/**
	 * 给角色授权
	 * 
	 * @param id
	 * @param roleId
	 * @param permissionId
	 */
	void createPermission(@Param("id") String id, @Param("roleId") String roleId,
			@Param("permissionId") String permissionId);

	/**
	 * 根据角色获取权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> permissionById(String roleId);
	/**
	 * 根据用户获取角色
	 * 
	 * @param userName
	 * @return
	 */
	String findRolesByUserName(String userName);

	List<Permission> permissionByParentId(@Param("roleId") String roleId, @Param("id")String id);

	List<Permission> MenupermissionByRoleId(String roleId);

	void deleteRolePermission(@Param("id") String id);
}