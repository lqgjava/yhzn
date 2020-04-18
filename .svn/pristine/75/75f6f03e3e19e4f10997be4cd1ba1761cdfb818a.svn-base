package com.yhzn.dao.customer;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.customer.CustomerProj;
import com.yhzn.model.customer.PriceBill;

/**
 * 客户项目信息dao
 * @author liany
 *
 */
public interface CustomerProjDao {
	
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
	public void insertCustomerProj(CustomerProj customerProj);
	
	/**
	 * 修改客户项目信息
	 * @param customerProj
	 * @param request
	 * @return
	 */
	public void editCustomerProj(CustomerProj customerProj);
	
	/**
	 * 根据ID查询客户项目信息
	 * @param id
	 */
	public CustomerProj queryCustomerProjById(String id);

	/**
	 * 新增项目报价清单产品信息
	 * @param priceBill
	 */
	public void addPriceBill(PriceBill priceBill);
	
	/**
	 * 查询清单产品详细信息
	 * @param id
	 */
	public List<PriceBill> queryPriceBillList(String id);
	
	/**
	 * 删除项目报价清单产品信息
	 * @param id
	 */
	public void deletePriceBillByProjId(String id);
	
	/**
	 * 更新清单状态
	 * @param map
	 */
	public void updateCustomerProjStatus(Map map);
}
