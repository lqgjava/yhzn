package com.yhzn.service.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.User;

public interface UserService {
	
	/**
	 * 查询用户信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	List<User> querySysUserList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 删除用户信息
	 * @param id
	 */
	public void deleteUserById(String id);
	
	/**
	 * 查询用户信息
	 * @param id
	 */
	public User findUserById(String id);
	
	/**
	 * 验证用户名密码
	 * @param para
	 * @return
	 */
	public User checkUser(HashMap<String,String> para);

	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	public int saveUser(User user);

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUser(User user);

	/**
	 * 根据账号查询用户信息
	 * @param username
	 * @return
	 */
	public User findUserByUserName(String username);
	Set<String> findRolesByUserName(String userName);//根据用户名获取用户所有角色
	Set<String> findPermissionsByUserName(String userName);//根据用户名获取用户所有权限
	

	User getUserByUserName(String userName);

	
}


