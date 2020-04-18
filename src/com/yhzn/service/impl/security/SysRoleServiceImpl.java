package com.yhzn.service.impl.security;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.security.SysRoleDao;
import com.yhzn.model.security.Permission;
import com.yhzn.model.security.SysModule;
import com.yhzn.model.security.SysRole;
import com.yhzn.model.security.SysRolePermis;
import com.yhzn.model.security.User;
import com.yhzn.service.security.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	public SysRoleDao sysRoleDao;

	/**
	 * 查询角色信息列表
	 * 
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<SysRole> querySysRoleList(PageBounds bounds, Map<String, Object> parameter) {
		return sysRoleDao.querySysRoleList(bounds, parameter);
	}

	/**
	 * 删除角色信息
	 * 
	 * @param id
	 */
	@Override
	public void deleteRoleById(String id) {
		// 没选择权限则删除该角色下所有权限
		sysRoleDao.deleteSysRolePermisById(id);
		// 删除角色
		sysRoleDao.deleteRoleById(id);
	}

	/**
	 * 保存角色信息
	 * 
	 * @param sysRole
	 * @return
	 */
	@Override
	public int saveSysRole(SysRole sysRole, User user) {
		sysRole.setId(UUID.randomUUID().toString().replaceAll("-", ""));// id
		sysRole.setCreateName(user.getTrueName());// 创建人姓名
		return sysRoleDao.saveSysRole(sysRole);
	}

	/**
	 * 更新角色信息
	 * 
	 * @param sysRole
	 * @return
	 */
	@Override
	public int updateSysRole(SysRole sysRole, User user) {
		// TODO Auto-generated method stub
		sysRole.setModifyName(user.getTrueName());// 修改人姓名
		return sysRoleDao.updateSysRole(sysRole);
	}

	/**
	 * 查询角色信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public SysRole findSysRoleById(String id) {
		// TODO Auto-generated method stub
		return sysRoleDao.findSysRoleById(id);
	}

	/**
	 * 获取模块根结点列表
	 * 
	 * @return
	 */
	@Override
	public List<SysModule> findModuleTree(String id) {
		// TODO Auto-generated method stub
		return sysRoleDao.findModuleTree(id);
	}

	/**
	 * 获取子节点列表
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<SysModule> findNodesById(Map<String, String> map) {
		// TODO Auto-generated method stub
		return sysRoleDao.findNodesById(map);
	}

	/**
	 * 保存权限
	 * 
	 * @param roleId
	 * @param moduleIds
	 */
	@Override
	public void savePermis(String roleId, String moduleIds) {
		// 判断是否选择了模块
		if ("".equals(moduleIds) || null == moduleIds) {
			// 没选择权限则删除该角色下所有权限
			sysRoleDao.deleteSysRolePermisById(roleId);
		} else {
			String idArray[] = moduleIds.split(",");
			// 先删除角色下所有权限，重新添加权限
			sysRoleDao.deleteSysRolePermisById(roleId);
			for (int i = 0; i < idArray.length; i++) {
				SysRolePermis srp = new SysRolePermis();
				srp.setId(UUID.randomUUID().toString().replaceAll("-", ""));// 主键id
				srp.setModuleId(idArray[i]);// 模块id
				srp.setRoleId(roleId);// 角色id
				srp.setCreateName("sys");// 创建人
				// 保存角色权限
				sysRoleDao.saveSysRolePermis(srp);
			}
		}
	}

	/**
	 * 获取角色列表
	 */
	@Override
	public List<SysRole> findSysRoleList() {
		// TODO Auto-generated method stub
		return sysRoleDao.findSysRoleList();
	}

	/**
	 * 查询用户权限列表
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<SysRolePermis> queryUserPermisListById(String id) {
		// TODO Auto-generated method stub
		return sysRoleDao.queryUserPermisListById(id);
	}

	@Override
	public void deletePermissionByRoleId(String roleId) {

		sysRoleDao.deletePermissionByRoleId(roleId);
	}

	@Override
	public void createPermission(String id, String roleId, String permissionId) {

		sysRoleDao.createPermission(id, roleId, permissionId);

	}

	@Override
	public List<Permission> permissionById(String roleId) {
		//获取角色菜单权限
		List<Permission> per = sysRoleDao.MenupermissionByRoleId(roleId);
		for (Permission permission : per) {
			//获取区域权限
			List<Permission> permiss = sysRoleDao.permissionByParentId(roleId,permission.getId());
			for (Permission perm : permiss) {
				//获取按钮权限
				List<Permission> permissi = sysRoleDao.permissionByParentId(roleId,perm.getId());
				perm.setChildren(permissi);
			}
		permission.setChildren(permiss);
		}
		return per;
	}
	@Override
	public List<Permission> permissionById2(String roleId) {
		//获取角色菜单权限
		return sysRoleDao.permissionById(roleId);
		
	}
	@Override
	public String findRolesByUserName(String userName) {
		
		return sysRoleDao.findRolesByUserName(userName);
	}

	@Override
	public void deleteRolePermission(String id) {
		sysRoleDao.deleteRolePermission(id);
		
	}

}
