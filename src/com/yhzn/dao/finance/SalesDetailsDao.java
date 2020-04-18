package com.yhzn.dao.finance;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.finance.SalesDetails;

/**
 * 
 * @author Liany
 * 销售合同详情接口
 */
public interface SalesDetailsDao {
	/**
	* 添加一条销售合同详情记录
	* @param elemType 财务采购合同对象
	* @return int 执行成功的数量
	* @throws DataAccessException
	*/
	 void insSalesDetails(SalesDetails SalesDetails);
	
	/**
	 * 查询销售合同详情记录
	 * @param
	 * @return
	 */
	List<SalesDetails> querySalesDetailsList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 修改销售合同详情记录
	 * @param financePurchaseContract
	 */
	void updateSalesDetails(SalesDetails SalesDetails);
	
	/**
	 * 根据id查询销售合同详情记录
	 * @param id
	 * @return
	 */
	SalesDetails selSalesDetailsById(String id);
	
	/**
	 * 根据id删除销售合同详情记录
	 * @param id
	 */
	void delSalesDetailsById(String id);
}
