package com.yhzn.dao.person;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.person.Person;

public interface PersonDao {

	/**
	 * 查询用户信息列表
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
	 */
	public void insertPersonInfo(Person person);
	
	/**
	 * 修改人员信息
	 * @param person
	 */
	public void editPersonInfo(Person person);
	
	/**
	 * map查询用户信息列表
	 * @param map
	 * @return
	 */
	public List<Person> queryPersonListByMap( Map<String, Object> map);
	
	/**
	 * 根据ID查询人员信息
	 * @param id
	 * @return
	 */
	public Person queryPersonInfoById(String id);
}
