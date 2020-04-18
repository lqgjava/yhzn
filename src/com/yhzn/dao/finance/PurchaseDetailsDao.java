package com.yhzn.dao.finance;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.finance.PurchaseContract;
import com.yhzn.model.finance.PurchaseDetails;

/**
 * 
 * @author Liany
 *采购合同详情接口
 */
public interface PurchaseDetailsDao {
	/**
	* 添加一条采购合同详情记录
	* @param elemType 财务采购合同对象
	* @return int 执行成功的数量
	* @throws DataAccessException
	*/
	 void insPurchaseDetails(PurchaseDetails purchaseDetails);
	
	/**
	 * 查询采购合同详情记录
	 * @param
	 * @return
	 */
	List<PurchaseDetails> queryPurchaseDetailsList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 修改采购合同详情记录
	 * @param financePurchaseContract
	 */
	void updatePurchaseDetails(PurchaseDetails purchaseDetails);
	
	/**
	 * 根据id查询采购合同详情记录
	 * @param id
	 * @return
	 */
	PurchaseDetails selPurchaseDetailsById(String id);
	
	/**
	 * 根据id删除采购合同详情记录
	 * @param id
	 */
	void delPurchaseDetailsById(String id);
}
