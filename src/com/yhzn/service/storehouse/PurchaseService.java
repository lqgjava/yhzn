package com.yhzn.service.storehouse;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.customer.CustomerProj;
import com.yhzn.model.customer.PriceBill;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.model.storehouse.Purchase;

/**
 * 采购管理接口
 * @author liany
 *
 */
public interface PurchaseService {

	/**
	 * 采购清单信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<Purchase> queryPurchaseList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 删除采购清单信息
	 * @param id
	 */
	public void deletePurchaseById(String id);
	
	/**
	 * 新增采购清单信息
	 * @param purchase
	 * @param user
	 * @return
	 */
	public void insertPurchase(Purchase purchase,User user);
	
	/**
	 * 审核采购清单
	 * @param purchase
	 * @param user
	 */
	public void checkPurchase(Purchase purchase,User user);
	
	/**
	 * 修改采购清单信息
	 * @param purchase
	 * @param user
	 * @return
	 */
	public void editPurchase(Purchase purchase,User user);
	
	/**
	 * 根据ID查询采购清单信息
	 * @param id
	 */
	public Purchase queryPurchaseById(String id);
	
	/**
	 * 查询采购清单产品详细信息
	 * @param id
	 * @return
	 */
	public List<PurBillModel> queryPurBillList(String id);
	
	/**
	 * 新增采购清单信息
	 * @param map
	 * @param list
	 */
	public void addPurBill(List<PurBillModel> list,Map<String, String> map,User user);
	
	/**
	 * 修改采购清单信息
	 * @param map
	 * @param list
	 */
	public void editPurBill(List<PurBillModel> list,Map<String, String> map,User user);
	
	/**
	 * 采购清单信息
	 * @param map
	 * @return
	 */
	public List<Purchase> queryPurInfoList(Map map);
	
}
