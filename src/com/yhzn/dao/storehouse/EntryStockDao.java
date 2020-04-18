package com.yhzn.dao.storehouse;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.storehouse.EntryStock;
import com.yhzn.model.storehouse.ProductModel;
import com.yhzn.model.storehouse.PurBillModel;

/**
 * 入库管理DAO
 * @author liany
 *
 */
public interface EntryStockDao {

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
	public void insertEntryStock(EntryStock entryStock);
	
	/**
	 * 修改入库清单信息
	 * @param entryStock
	 * @param user
	 * @return
	 */
	public void editEntryStock(EntryStock entryStock);
	
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
	public List<PurBillModel> queryETPurBillList(String id);
	
	/**
	 * 新增入库清单信息
	 * @param map
	 * @param list
	 */
	public void addETPurBill(PurBillModel purBillModel);
	
	/**
	 * 删除入库清单产品信息
	 * @param id
	 */
	public void deleteETPurBillByPurId(String id);
	
	/**
	 * 更新入库清单状态
	 * @param map
	 */
	public void updateEntryStockStatus(Map map);
	
	/**
	 * 更新产品入库状态
	 * @param map
	 */
	public void updatePurBillStatus(Map map);
	
	/**
	 * 更新产品库存信息
	 * @param map
	 */
	public void updateProductAmount(Map map);
	
	/**
	 * @param map
	 * @return 
	 */
	public Map queryPurBillMapById(String id);
	
	/**
	 * 查询清单未入库状态数量
	 * @param purId
	 * @return
	 */
	public int queryPurBillStatusCount(String purId);
	
	/**
	 * 更新产品入库状态
	 * @param map
	 */
	public void updatePurchaseEntryStatus(Map map);
	/**
	 * 更新采购清单产品全部入库状态
	 * 
	 */
	public void updatePurBillAllStatus(@Param("ids") String [] ids,@Param("status") String status,@Param("modifyName")String modifyName);
	/**
	 * 查询采购清单产品信息
	 * 
	 */
	public List<ProductModel> queryPurBillMapByIds(@Param("ids") String[] ids);
	/**
	 * 更新库存
	 * 
	 */
	public void updateProductAmountById(@Param("stockAmount")String stockAmount,@Param("modifyName")String modifyName, @Param("pId")String pId);
/**
 * 
 * @param id
 * @param modifyName
 */
	public void updatePurchaseEntryAllStatus(@Param("id") String id, @Param("modifyName")String modifyName);

public void updatePurEntryAmountById(Map map);
}
