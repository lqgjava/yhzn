package com.yhzn.service.person;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.person.Person;
import com.yhzn.model.security.User;

/**
 * 人员管理接口
 * @author liany
 *
 */
public interface PersonService {

	/**
	 * 查询人员信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	List<Person> queryPersonList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 删除人员信息
	 * @param id
	 */
	public void deletePersonById(String id);
	
	/**
	 * 新增人员信息
	 * @param person
	 * @param user
	 */
	public void insertPersonInfo(Person person,User user);
	
	/**
	 * 修改人员信息
	 * @param person
	 * @param user
	 */
	public void editPersonInfo(Person person,User user);
	
	/**
	 * map查询用户信息列表
	 * @param map
	 * @return
	 */
	public List<Person> queryPersonListByMap( Map<String, Object> map);
}
