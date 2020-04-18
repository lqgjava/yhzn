package com.yhzn.service.impl.storehouse;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.storehouse.StockCheckDao;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.OutBillModel;
import com.yhzn.model.storehouse.Product;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.model.storehouse.StockCheck;
import com.yhzn.service.storehouse.StockCheckService;

/**
 * 库存核算实现类
 * @author liany
 *
 */
@Service
public class StockCheckServiceImpl implements StockCheckService {

	//库存核算dao
	@Resource
	private StockCheckDao stockCheckDao;
	
	/**
	 * 查询库存核算列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<StockCheck> queryStockCheckList(PageBounds bounds,Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return stockCheckDao.queryStockCheckList(bounds, parameter);
	}
	
	/**
	 * 修改库存核算信息
	 * @param user
	 * @param stockCheck
	 */
	@Override
	public void editStockCheckInfo(StockCheck stockCheck, User user) {
		// TODO Auto-generated method stub
		stockCheck.setModifyName(user.getTrueName());//修改人姓名
		stockCheckDao.editStockCheckInfo(stockCheck);
	}

	/**
	 * 查询产品出库信息
	 * @param id
	 * @return
	 */
	@Override
	public List<OutBillModel> queryOutRecordList(String id) {
		// TODO Auto-generated method stub
		return stockCheckDao.queryOutRecordList(id);
	}

	/**
	 * 查询产品入库信息
	 * @param id
	 * @return
	 */
	@Override
	public List<PurBillModel> queryEntryRecordList(String id) {
		// TODO Auto-generated method stub
		return stockCheckDao.queryEntryRecordList(id);
	}
}
