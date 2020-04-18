package com.yhzn.dao.finance;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.finance.PurchaseContract;
import com.yhzn.model.person.Person;

public interface PurchaseContractDao {
	/**
	* 添加一条财务采购合同记录
	* @param elemType 财务采购合同对象
	* @return int 执行成功的数量
	* @throws DataAccessException
	*/
	 void insertFinancePurchaseContract(PurchaseContract financePurchaseContract);
	
	/**
	 * 查询采购合同记录
	 * @param
	 * @return
	 */
	List<PurchaseContract> queryFinancePurchaseContractList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 修改采购合同信息
	 * @param financePurchaseContract
	 */
	void updateFinancePurchaseContract(PurchaseContract financePurchaseContract);
	
	/**
	 * 根据id查询采购合同信息
	 * @param id
	 * @return
	 */
	PurchaseContract selFinancePurchaseContractById(String id);
	
	/**
	 * 根据id删除采购合同信息
	 * @param id
	 */
	void delFinancePurchaseContractById(String id);
	
}
