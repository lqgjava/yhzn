package com.yhzn.dao.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.User;
import com.yhzn.model.security.UserRoleModule;

public interface UserDao {

	/**
	 * 查询用户信息列表
	 * 
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	List<User> querySysUserList(PageBounds bounds, Map<String, Object> parameter);

	/**
	 * 删除用户信息
	 * 
	 * @param id
	 */
	public void deleteUserById(String id);

	/**
	 * 查询用户信息
	 * 
	 * @param id
	 */
	public User findUserById(String id);

	/**
	 * 验证用户名密码
	 * 
	 * @param para
	 * @return
	 */
	public User checkUser(HashMap<String, String> para);

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int saveUserInfo(User user);

	/**
	 * 根据账号查询用户信息
	 * 
	 * @param username
	 * @return
	 */
	public User findUserByUserName(String username);

	List<String> findRolesByUserName(String userName);

	List<String> findPermissionsByUserName(String userName);

//添加用户关联
	void insertUserRole(UserRoleModule ur);

//修改用户关联
	void updateUserRole(@Param("id") String id, @Param("roleId") String roleId);

//查找是否拥有角色
	int countRole(@Param("userId") String userId);

	int countUserName(String userName);
}