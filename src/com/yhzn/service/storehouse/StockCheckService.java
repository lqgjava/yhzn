package com.yhzn.service.storehouse;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.OutBillModel;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.model.storehouse.StockCheck;

/**
 * 库存核算接口
 * @author liany
 *
 */
public interface StockCheckService {

	/**
	 * 查询库存核算列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<StockCheck> queryStockCheckList(PageBounds bounds, Map<String, Object> parameter);

	/**
	 * 修改库存核算信息
	 * @param user
	 * @param stockCheck
	 */
	public void editStockCheckInfo(StockCheck stockCheck, User user);
	
	/**
	 * 查询产品出库信息
	 * @param id
	 * @return
	 */
	public List<OutBillModel> queryOutRecordList(String id);
	/**
	 * 查询产品入库信息
	 * @param id
	 * @return
	 */
	public List<PurBillModel> queryEntryRecordList(String id);
}