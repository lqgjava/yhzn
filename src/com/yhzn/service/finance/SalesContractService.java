package com.yhzn.service.finance;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.finance.SalesContract;
import com.yhzn.model.security.User;

/**
 * 销售合同服务接口层
 * @author Liany
 *
 */
public interface SalesContractService{
	/**
	* 添加一条销售合同记录
	* @param aFinancePurchaseContract 添加的财务采购合同对象
	*/
	Map<String,Object> addFinancialSalesContract(SalesContract FinancialSalesContract,User user);
	
	/**
	 * 分页查询销售合同信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	List<SalesContract> queryFinancialSalesContractList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 修改销售合同信息
	 * @param financePurchaseContract
	 * @param user
	 */
	Map<String,Object> updateFinancialSalesContract(SalesContract financialSalesContract,User user);
	
	/**
	 * 根据id查询销售合同详情
	 * @param id
	 * @return
	 */
	SalesContract selFinancialSalesContractById(String id);
	
	/**
	 * 根据id删除销售合同
	 * @param id
	 * @param user
	 */
	void delFinancialSalesContractById(String id,User user);
}
