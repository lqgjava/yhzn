package com.yhzn.service.impl.security;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.person.PersonDao;
import com.yhzn.dao.security.PermissiondAO;
import com.yhzn.dao.security.SysRoleDao;
import com.yhzn.dao.security.UserDao;
import com.yhzn.model.person.Person;
import com.yhzn.model.security.User;
import com.yhzn.model.security.UserRoleModule;
import com.yhzn.service.security.UserService;

/**
 * 用户管理实现类
 * @author liany
 *
 */
@Service
public class UserServiceImpl implements UserService{
	/**
	 * 用户管理dao
	 */
	@Resource
	public UserDao userDao;
	//人员管理dao
	@Resource
	private PersonDao personDao;
	@Resource
	private PermissiondAO permissionDao;
	@Resource
	private SysRoleDao sysRoleDao;
	
	/**
	 * 查询用户信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<User> querySysUserList(PageBounds bounds,Map<String, Object> parameter) {
		return userDao.querySysUserList(bounds, parameter);
	}

	/**
	 * 删除用户信息
	 * @param id
	 */
	@Override
	public void deleteUserById(String id) {
		userDao.deleteUserById(id);
	}

	/**
	 * 查询用户信息
	 * @param id
	 */
	@Override
	public User findUserById(String id) {
		return userDao.findUserById(id);
	}

	/**
	 * 用户名密码认证
	 */
	@Override
	public 	User checkUser(HashMap<String,String> para){
		return userDao.checkUser(para);
	}

	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 */
	@Override
	public int saveUser(User user) {
		//根据人员id查询人员详细信息
		Person person = personDao.queryPersonInfoById(user.getPersonId());
		if(person!=null){
			user.setTrueName(person.getName());//姓名
			user.setDept(person.getDept());//部门
		}
		//插入用户角色关联
		UserRoleModule ur=new UserRoleModule(UUID.randomUUID().toString().replaceAll("-", ""),user.getId(),user.getRoleId());
		userDao.insertUserRole(ur);
		return userDao.saveUserInfo(user);
	}

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	@Override
	public int updateUser(User user) {
		//判断userId是否拥有角色
		int count =userDao.countRole(user.getId());
		if(count>0) {
			//更改用户和角色的关联
			userDao.updateUserRole(user.getId(),user.getRoleId());
		}else {
			//插入用户角色关联
			UserRoleModule ur=new UserRoleModule(UUID.randomUUID().toString().replaceAll("-", ""),user.getId(),user.getRoleId());
			userDao.insertUserRole(ur);
		}
		
		return userDao.updateUserInfo(user);
	}

	/**
	 * 根据账号查询用户信息
	 * @param username
	 * @return
	 */
	@Override
	public User findUserByUserName(String username) {
		return userDao.findUserByUserName(username);
	}

	@Override
	public Set<String> findRolesByUserName(String userName) {
		return new HashSet<String>(userDao.findRolesByUserName(userName));
	}


	@Override
	public Set<String> findPermissionsByUserName(String userName) {
		return new HashSet<String>(userDao.findPermissionsByUserName(userName));
	}

	


	@Override
	public User getUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	
}
