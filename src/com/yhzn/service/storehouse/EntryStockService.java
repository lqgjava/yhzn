package com.yhzn.service.storehouse;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.EntryStock;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.model.storehouse.Purchase;

/**
 * 入库管理接口
 * @author liany
 *
 */
public interface EntryStockService {
	
	/**
	 * 入库清单信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<EntryStock> queryEntryStockList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 入库清单信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<PurBillModel> queryPurBillList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 删除入库清单信息
	 * @param id
	 */
	public void deleteEntryStockById(String id);
	
	/**
	 * 新增入库清单信息
	 * @param entryStock
	 * @param user
	 * @return
	 */
	public void insertEntryStock(EntryStock entryStock,User user);
	
	/**
	 * 审核入库清单信息
	 * @param entryStock
	 * @param user
	 * @return
	 */
	public void checkEntryStock(EntryStock entryStock,User user);
	/**
	 * 修改入库清单信息
	 * @param entryStock
	 * @param user
	 * @return
	 */
	public void editEntryStock(EntryStock entryStock,User user);
	
	/**
	 * 根据ID查询入库清单信息
	 * @param id
	 */
	public EntryStock queryEntryStockById(String id);
	
	/**
	 * 查询入库清单产品详细信息
	 * @param id
	 * @return
	 */
	public List<PurBillModel> queryPurBillList(String id);
	
	/**
	 * 新增入库清单信息
	 * @param map
	 * @param list
	 */
	public void addPurBill(List<PurBillModel> list,Map map);
	
	/**
	 * 清单入库
	 * @param map
	 * @param user
	 */
	public void updatePurBill(Map map,User user);

	public void updatePurBillAll(String [] ids,String status,String modifyName);
}
