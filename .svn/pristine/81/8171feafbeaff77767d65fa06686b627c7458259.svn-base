package com.yhzn.service.impl.storehouse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.storehouse.EntryStockDao;
import com.yhzn.dao.storehouse.ProductDao;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.EntryStock;
import com.yhzn.model.storehouse.ProductModel;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.service.common.SysSequenceService;
import com.yhzn.service.storehouse.EntryStockService;

/**
 * 入库管理实现类
 * 
 * @author liany
 *
 */
@Service
public class EntryStockServiceImpl implements EntryStockService {

	// 入库管理dao
	@Resource
	private EntryStockDao entryStockDao;
	@Autowired
	private SysSequenceService sysSequenceService;

	/**
	 * 入库清单信息列表
	 * 
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<EntryStock> queryEntryStockList(PageBounds bounds, Map<String, Object> parameter) {
		return entryStockDao.queryEntryStockList(bounds, parameter);
	}

	/**
	 * 入库清单信息列表
	 * 
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<PurBillModel> queryPurBillList(PageBounds bounds, Map<String, Object> parameter) {
		return entryStockDao.queryPurBillList(bounds, parameter);
	}

	/**
	 * 删除入库清单信息
	 * 
	 * @param id
	 */
	public void deleteEntryStockById(String id) {
		entryStockDao.deleteEntryStockById(id);
	}

	/**
	 * 新增入库清单信息
	 * 
	 * @param entryStock
	 * @param user
	 * @return
	 */
	public void insertEntryStock(EntryStock entryStock, User user) {
		entryStock.setEntryNo("RK" + sysSequenceService.getNextNo("ENTRY_STOCK", user));
		entryStock.setId(UUID.randomUUID().toString().replaceAll("-", ""));// id
		entryStock.setCreateName(user.getTrueName());// 创建人姓名

		entryStockDao.insertEntryStock(entryStock);
	}

	/**
	 * 新增入库清单信息
	 * 
	 * @param entryStock
	 * @param user
	 * @return
	 */
	public void checkEntryStock(EntryStock entryStock, User user) {
		entryStock.setModifyName(user.getTrueName());// 修改人姓名
		entryStock.setCheckUser(user.getTrueName());// 审核人
		entryStock.setCheckUserId(user.getId());// 审核人id
		entryStockDao.editEntryStock(entryStock);
	}

	/**
	 * 修改入库清单信息
	 * 
	 * @param entryStock
	 * @param user
	 * @return
	 */
	public void editEntryStock(EntryStock entryStock, User user) {
		// TODO Auto-generated method stub
		entryStock.setModifyName(user.getTrueName());// 修改人姓名

		entryStockDao.editEntryStock(entryStock);
	}

	/**
	 * 根据ID查询入库清单信息
	 * 
	 * @param id
	 */
	public EntryStock queryEntryStockById(String id) {
		return entryStockDao.queryEntryStockById(id);
	}

	/**
	 * 查询入库清单产品详细信息
	 * 
	 * @param id
	 * @return
	 */
	public List<PurBillModel> queryPurBillList(String id) {
		return entryStockDao.queryETPurBillList(id);
	}

	/**
	 * 新增入库清单信息
	 * 
	 * @param map
	 * @param list
	 */
	public void addPurBill(List<PurBillModel> list, Map map) {
		if (list != null && list.size() > 0) {
			// 先删除原来的采购清单信息
			entryStockDao.deleteETPurBillByPurId(map.get("id").toString());

			for (int i = 0; i < list.size(); i++) {
				PurBillModel purBillModel = list.get(i);

				purBillModel.setSerialNumber(String.valueOf(i + 1));
				purBillModel.setId(UUID.randomUUID().toString().replaceAll("-", ""));// id
				purBillModel.setCreateName(map.get("modifyName").toString());// 创建人姓名

				entryStockDao.addETPurBill(purBillModel);// 插入入库清单信息
			}

			// 更新入库清单状态 1-未提交，2-已提交
			entryStockDao.updateEntryStockStatus(map);
		}
	}

	/**
	 * 入库清单
	 * 
	 * @param map
	 * @param user
	 */
	public void updatePurBill(Map map, User user) {
		// 查询产品信息
		Map pmb = entryStockDao.queryPurBillMapById(map.get("id").toString());
		map.put("pId", pmb.get("PID").toString());// id
		int stockAmount = 0;
		int amount = 0;
		int entrycount = 0;
		int ystockAmount = 0;
		String purId = "";
		// 获取库存数量
		if (null != pmb.get("STOCKAMOUNT")) {
			stockAmount = Integer.parseInt(pmb.get("STOCKAMOUNT").toString());
		}
		// 获取总入库数量
		if (null != pmb.get("AMOUNT")) {
			amount = Integer.parseInt(pmb.get("AMOUNT").toString());
		}
		// 获取已入库数量
		if (null != pmb.get("ENTRYAMOUNT")) {
			ystockAmount = Integer.parseInt(pmb.get("ENTRYAMOUNT").toString());
		}
		// 前台输入入库数
		if (null != map.get("entrycount")) {
			entrycount = Integer.parseInt(map.get("entrycount").toString());
		}	
		map.put("amount", (entrycount + stockAmount) + "");// 总库存数量=入库数量+当前库存数量
		map.put("entryAmount", (entrycount+ystockAmount) + "");// 入库数量
		
			entryStockDao.updatePurEntryAmountById(map);// 修改采购单详情入库数量
			// 更新产品库存数量
			entryStockDao.updateProductAmount(map);
		
			entryStockDao.updatePurEntryAmountById(map);// 修改采购单详情入库数量
			// 更新产品库存数量
			entryStockDao.updateProductAmount(map);
			 if(entrycount==amount-ystockAmount){//输入入库数=剩余入库数（该产品全部入库）
			if (null != pmb.get("PURID")) {
				purId = pmb.get("PURID").toString();
				// 更新产品入库状态
				entryStockDao.updatePurBillStatus(map);
				// 更新采购单入库状态
				int count = entryStockDao.queryPurBillStatusCount(purId);
				if (count == 0) {
					map.put("purId", purId);
					entryStockDao.updatePurchaseEntryStatus(map);
				}
			}
		}
		

	

	}

	/**
	 * 采购清单入库
	 * 
	 * @param ids
	 * @param status
	 * @param nodifyName
	 */
	public void updatePurBillAll(String[] ids, String status, String modifyName) {
		// 更改产品入库状态
		entryStockDao.updatePurBillAllStatus(ids, status, modifyName);
		// 查询产品信息

		List<ProductModel> productList = entryStockDao.queryPurBillMapByIds(ids);
		for (ProductModel product : productList) {
			int stockAmount = 0;
			int amount = 0;
			int entryAmount=0;
			if (null != product.getStockAmount()) {// 当前产品库存数
				stockAmount = Integer.parseInt(product.getStockAmount());
			} 
			if (null != product.getAmount()) {// 产品入库数
				amount = Integer.parseInt(product.getAmount());
			}
			if(null!=product.getEntryAmount()) {//已入库数量
				entryAmount= Integer.parseInt(product.getEntryAmount());
			}
			// 总库存数量=入库数量+总库存数量-已入库数量
			stockAmount = stockAmount + amount-entryAmount;
			product.setModifyName(modifyName);
			// 更新产品库存数量
			entryStockDao.updateProductAmountById(stockAmount + "", modifyName, product.getPid());
			// 更新采购单入库状态
			int count = entryStockDao.queryPurBillStatusCount(product.getId());
			if (count == 0) {
				entryStockDao.updatePurchaseEntryAllStatus(product.getId(), modifyName);
			}
		}
	}

}
