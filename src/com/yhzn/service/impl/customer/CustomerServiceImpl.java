package com.yhzn.service.impl.customer;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.customer.CustomerDao;
import com.yhzn.model.customer.Customer;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.service.customer.CustomerService;

/**
 * 客户管理实现类
 * @author liany
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	//客户管理dao
	@Resource
	private CustomerDao customerDao;

	/**
	 * 查询客户信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<Customer> queryCustomerList(PageBounds bounds,Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return customerDao.queryCustomerList(bounds, parameter);
	}

	/**
	 * 删除客户信息
	 * @param id
	 */
	@Override
	public void deleteCustomerById(String id) {
		// TODO Auto-generated method stub
		customerDao.deleteCustomerById(id);
	}

	/**
	 * 新增客户信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@Override
	public void insertCustomerInfo(Customer customer, User user) {
		// TODO Auto-generated method stub
		customer.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
		customer.setCreateName(user.getTrueName());//创建人姓名
		
		customerDao.insertCustomerInfo(customer);
	}

	/**
	 * 修改客户信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@Override
	public void editCustomerInfo(Customer customer, User user) {
		// TODO Auto-generated method stub
		customer.setModifyName(user.getTrueName());//修改人姓名
		
		customerDao.editCustomerInfo(customer);
		
	}

	/**
	 * 根据ID查询客户信息
	 * @param id
	 */
	@Override
	public Customer queryCustomerById(String id) {
		// TODO Auto-generated method stub
		return customerDao.queryCustomerById(id);
	}

	@Override
	public List queryCustomerInfoList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return customerDao.queryCustomerInfoList(map);
	}
	
	/**
	 * 查询用户下拉框信息
	 * @return
	 */
	@Override
	public List<SysDict> customerCombobox() {
		return customerDao.customerCombobox();
	}
}
