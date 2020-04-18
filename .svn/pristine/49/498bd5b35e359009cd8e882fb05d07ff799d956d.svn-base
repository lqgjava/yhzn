package com.yhzn.service.customer;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.customer.CustomerProj;
import com.yhzn.model.customer.PriceBill;
import com.yhzn.model.security.User;

/**
 * 客户项目信息管理接口
 * @author liany
 *
 */
public interface CustomerProjService {
	
	/**
	 * 查询客户项目信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<CustomerProj> queryCustomerProjList(PageBounds bounds, Map<String, Object> parameter);

	/**
	 * 删除客户项目信息
	 * @param id
	 */
	public void deleteCustomerProjById(String id);
	
	/**
	 * 新增客户项目信息
	 * @param customerProj
	 * @param request
	 * @return
	 */
	public void insertCustomerProj(CustomerProj customerProj,User user);
	
	/**
	 * 修改客户项目信息
	 * @param customerProj
	 * @param request
	 * @return
	 */
	public void editCustomerProj(CustomerProj customerProj,User user);
	
	/**
	 * 根据ID查询客户项目信息
	 * @param id
	 */
	public CustomerProj queryCustomerProjById(String id);
	
	/**
	 * 新增项目报价清单信息
	 * @param map
	 * @param list
	 */
	public void addPriceBill(List<PriceBill> list,Map map);
	
	/**
	 * 查询清单产品详细信息
	 * @param id
	 * @return
	 */
	public List<PriceBill> queryPriceBillList(String id);
}
