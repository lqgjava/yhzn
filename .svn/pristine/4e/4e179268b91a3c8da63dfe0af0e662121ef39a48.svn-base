package com.yhzn.dao.storehouse;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.EntryStock;
import com.yhzn.model.storehouse.OutBillModel;
import com.yhzn.model.storehouse.OutStock;
import com.yhzn.model.storehouse.OutStockModel;
import com.yhzn.model.storehouse.PurBillModel;

/**
 * 出库管理DAO
 * @author liany
 *
 */
public interface OutStockDao {
	/**
	 * 出库清单信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<OutStock> queryOutStockList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 删除出库清单信息
	 * @param id
	 */
	public void deleteOutStockById(String id);
	
	/**
	 * 新增出库清单信息
	 * @param outStock
	 * @return
	 */
	public void insertOutStock(OutStock outStock);
	
	/**
	 * 修改出库清单信息
	 * @param outStock
	 * @return
	 */
	public void editOutStock(OutStock outStock);
	
	/**
	 * 根据ID查询出库清单信息
	 * @param id
	 */
	public OutStock queryOutStockById(String id);
	
	/**
	 * 查询出库清单产品详细信息
	 * @param id
	 * @return
	 */
	public List<OutBillModel> queryOutStockBillList(String id);
	
	/**
	 * 新增出库清单信息
	 * @param map
	 * @param list
	 */
	public void addOutStockBill(OutBillModel outBillModel);
	
	/**
	 * 删除出库清单产品信息
	 * @param id
	 */
	public void deleteOutStockBillByOutId(String id);
	
	/**
	 * 更新出库清单状态
	 * @param map
	 */
	public void updateOutStockStatus(Map map);
	
	/**
	 * 更新产品库存信息
	 * @param map
	 */
	public void updateOutProductAmount(Map map);
	
	/**
	 * 查询出库列表和产品信息
	 * @param id
	 * @return
	 */
	public List queryOutBillStockInfo(String id);

	public OutStockModel findCompanyNameByOutId(@Param("outNo") String outNo);

	public List<OutBillModel> queryOutStockBillListByOutNo(@Param("outNo")String outNo);
}
