package com.yhzn.service.storehouse;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.OutBillModel;
import com.yhzn.model.storehouse.OutStock;
import com.yhzn.model.storehouse.OutStockModel;
import com.yhzn.model.storehouse.PurBillModel;

/**
 * 出库管理接口
 * @author liany
 *
 */
public interface OutStockService {

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
	 * @param user
	 * @return
	 */
	public void insertOutStock(OutStock outStock,User user);
	
	/**
	 * 修改出库清单信息
	 * @param outStock
	 * @param user
	 * @return
	 */
	public void editOutStock(OutStock outStock,User user);
	
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
	public void addOutStockBill(List<OutBillModel> list,Map map,User user);
	
	/**
	 * 修改出库清单信息
	 * @param map
	 * @param list
	 */
	public void editOutStockBill(List<OutBillModel> list,Map map,User user);
	
	/**
	 * 审核出库
	 * @param outStock
	 * @param user
	 */
	public void checkOutStock(OutStock outStock,User user);
	
	/**
	 * 清单出库
	 * @param map
	 * @param user
	 */
	public Map<String,String> updateOutBill(Map map,User user);

	public OutStockModel findCompanyNameByOutId(String outNo);

	public List<OutBillModel> queryOutStockBillListByOutNo(String outNo);
}
