package com.yhzn.dao.customer;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.customer.Customer;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;

/**
 * 客户信息dao
 * @author liany
 *
 */
public interface CustomerDao {

	/**
	 * 查询客户信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	List<Customer> queryCustomerList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 删除客户信息
	 * @param id
	 */
	public void deleteCustomerById(String id);
	
	/**
	 * 新增客户信息
	 * @param customer
	 * @param request
	 * @return
	 */
	public void insertCustomerInfo(Customer customer);
	
	/**
	 * 修改客户信息
	 * @param customer
	 * @param request
	 * @return
	 */
	public void editCustomerInfo(Customer customer);
	
	/**
	 * 根据ID查询客户信息
	 * @param id
	 */
	public Customer queryCustomerById(String id);
	
	/**
	 * 查询客户信息list
	 * @param map
	 * @return
	 */
	public List<Customer> queryCustomerInfoList(Map map);
	
	/**
	 * 查询用户下拉框信息
	 * @return
	 */
	public List<SysDict> customerCombobox();
}
