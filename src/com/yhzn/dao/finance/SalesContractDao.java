package com.yhzn.dao.finance;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.finance.SalesContract;

/**
 * 财务销售合同数据访问接口层
 * @author Gavin
 * @since 2020/04/13
 */
public interface SalesContractDao{
	
	/**
	* 添加一条财务销售合同记录
	* @param elemType 财务销售合同对象
	* @return int 执行成功的数量
	* @throws DataAccessException
	*/
	void addFinancialSalesContract(SalesContract financialSalesContract);

	/**
	* 查询所有财务销售合同记录
	* @return 财务销售合同对象集
	* @throws DataAccessException
	*/
	public List<SalesContract> queryFinancialSalesContractList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 修改销售合同列表
	 * @param financialSalesContract
	 */
	void editFinancialSalesContract(SalesContract financialSalesContract);
	
	/**
	 * 根据id删除销售合同
	 * @param id
	 */
	void delFinancialSalesContractById(String id);
	
	/**
	 * 根据id查询销售合同
	 * @param id
	 * @return
	 */
	SalesContract selFinancialSalesContractById(String id);
	
	List<SalesContract> getSalesContractBycontractNo(String contractNo);
}
