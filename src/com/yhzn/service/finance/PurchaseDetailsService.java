package com.yhzn.service.finance;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.finance.PurchaseDetails;
import com.yhzn.model.security.User;

/**
 * 采购合同详情service接口
 * @author Liany
 *
 */
public interface PurchaseDetailsService {
	/**
	* 添加一条财务采购合同详情新记录
	* @param aFinancePurchaseDetails 添加的财务采购合同对象
	*/
	Map<String,Object> addPurchaseDetails(PurchaseDetails purchaseDetails,User user);
	
	/**
	 * 查询采购合同详情列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	List<PurchaseDetails> queryPurchaseDetailsList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 修改采购合同详情信息
	 * @param financePurchaseDetails
	 * @param user
	 */
	Map<String,Object> updatePurchaseDetails(PurchaseDetails purchaseDetails,User user);
	
	/**
	 * 根据id查询采购合同详情
	 * @param id
	 * @return
	 */
	PurchaseDetails selPurchaseDetailsById(String id);
	
	/**
	 * 根据id删除采购合同详情
	 * @param id
	 * @param user
	 */
	void delPurchaseDetailsById(String id,User user);
}
