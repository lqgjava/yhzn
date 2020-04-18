package com.yhzn.service.finance;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.finance.SalesDetails;
import com.yhzn.model.security.User;

/**
 * 
 * @author Liany
 * 销售合同详情service接口
 */
public interface SalesDetailsService {
	/**
	* 添加一条销售合同详情新记录
	* @param aFinanceSalesDetails 添加的财务采购合同对象
	*/
	Map<String,Object> addSalesDetails(SalesDetails SalesDetails,User user);
	
	/**
	 * 查询销售合同详情列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	List<SalesDetails> querySalesDetailsList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 修改销售合同详情信息
	 * @param financeSalesDetails
	 * @param user
	 */
	Map<String,Object> updateSalesDetails(SalesDetails SalesDetails,User user);
	
	/**
	 * 根据id查询销售合同详情
	 * @param id
	 * @return
	 */
	SalesDetails selSalesDetailsById(String id);
	
	/**
	 * 根据id删除销售合同详情
	 * @param id
	 * @param user
	 */
	void delSalesDetailsById(String id,User user);
}
