package com.yhzn.dao.storehouse;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.model.storehouse.Purchase;

/**
 * 采购管理DAO
 * @author liany
 *
 */
public interface PurchaseDao {

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
	 * @return
	 */
	public void insertPurchase(Purchase purchase);
	
	/**
	 * 修改采购清单信息
	 * @param purchase
	 * @return
	 */
	public void editPurchase(Purchase purchase);
	
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
	 * @param purBillModel
	 */
	public void addPurBill(PurBillModel purBillModel);
	
	/**
	 * 删除采购清单产品信息
	 * @param id
	 */
	public void deletePurBillByPurId(String id);
	
	/**
	 * 更新采购清单状态
	 * @param map
	 */
	public void updatePurchaseStatus(Map map);
	
	/**
	 * 采购清单信息
	 * @param map
	 * @return
	 */
	public List<Purchase> queryPurInfoList(Map map);
}
