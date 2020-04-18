package com.yhzn.service.impl.storehouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.storehouse.ProductDao;
import com.yhzn.dao.storehouse.PurchaseDao;
import com.yhzn.model.customer.PriceBill;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.model.storehouse.Purchase;
import com.yhzn.service.common.SysSequenceService;
import com.yhzn.service.storehouse.PurchaseService;
import com.yhzn.service.workbench.WorkbenchService;

/**
 * 采购管理实现类
 * 
 * @author liany
 *
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

	// 采购管理dao
	@Resource
	private PurchaseDao purchaseDao;
	@Autowired
	private SysSequenceService sysSequenceService;

	/**
	 * 采购清单信息列表
	 * 
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<Purchase> queryPurchaseList(PageBounds bounds, Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return purchaseDao.queryPurchaseList(bounds, parameter);
	}

	/**
	 * 删除采购清单信息
	 * 
	 * @param id
	 */
	@Override
	public void deletePurchaseById(String id) {
		// TODO Auto-generated method stub
		purchaseDao.deletePurchaseById(id);
	}

	/**
	 * 新增采购清单信息
	 * 
	 * @param purchase
	 * @param user
	 */
	@Override
	public void insertPurchase(Purchase purchase, User user) {
		purchase.setPurNo("CG" + sysSequenceService.getNextNo("PURCHASE", user));
		purchase.setId(UUID.randomUUID().toString().replaceAll("-", ""));// id
		purchase.setCreateName(user.getTrueName());// 创建人姓名
		purchase.setUserId(user.getId());
		purchase.setStatus("0");
		purchaseDao.insertPurchase(purchase);
	}

	/**
	 * 审核采购清单
	 * 
	 * @param purchase
	 * @param user
	 */
	public void checkPurchase(Purchase purchase, User user) {
		purchase.setModifyName(user.getTrueName());// 修改人姓名
		purchase.setCheckUser(user.getTrueName());// 审核人
		purchase.setCheckUserId(user.getId());// 审核人id
		purchaseDao.editPurchase(purchase);
	}

	/**
	 * 修改采购清单信息
	 * 
	 * @param purchase
	 * @param user
	 * @return
	 */
	@Override
	public void editPurchase(Purchase purchase, User user) {
		// TODO Auto-generated method stub
		purchase.setModifyName(user.getTrueName());// 修改人姓名

		purchaseDao.editPurchase(purchase);
	}

	/**
	 * 根据ID查询采购清单信息
	 * 
	 * @param id
	 */
	@Override
	public Purchase queryPurchaseById(String id) {
		// TODO Auto-generated method stub
		return purchaseDao.queryPurchaseById(id);
	}

	/**
	 * 查询采购清单产品详细信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<PurBillModel> queryPurBillList(String id) {
		// TODO Auto-generated method stub
		return purchaseDao.queryPurBillList(id);
	}

	/**
	 * 新增采购清单信息
	 * 
	 * @param map
	 * @param list
	 */

	public void addPurBill(List<PurBillModel> list, Map<String, String> map, User user) {
		// TODO Auto-generated method stub

		Purchase purchase = new Purchase();
		if(StringUtils.isBlank(map.get("purNo"))) {
			purchase.setPurNo("CG" + sysSequenceService.getNextNo("PURCHASE", user));// 采购编号
		}else {
			purchase.setPurNo(map.get("purNo"));
		}
		purchase.setId(UUID.randomUUID().toString().replaceAll("-", ""));// id
		purchase.setCreateName(user.getTrueName());// 创建人姓名
		purchase.setUserId(user.getId());// 创建人id
		purchase.setStatus(map.get("status").toString());// 清单状态
		purchase.setEntryStatus("0");// 入库状态默认 未入库
		purchaseDao.insertPurchase(purchase);

		if (list != null && list.size() > 0) {
			// 先删除原来的采购清单信息

			for (int i = 0; i < list.size(); i++) {
				PurBillModel purBillModel = list.get(i);
				purBillModel.setSerialNumber(String.valueOf(i + 1));// 序号
				purBillModel.setId(UUID.randomUUID().toString().replaceAll("-", ""));// id
				purBillModel.setCreateName(map.get("modifyName").toString());// 创建人姓名
				purBillModel.setPurId(purchase.getId());// 采购单id
				purBillModel.setPurNo(purchase.getPurNo());// 采购单编号

				purchaseDao.addPurBill(purBillModel);// 插入采购清单信息
			}
		}
	}

	/**
	 * 修改采购清单信息
	 * 
	 * @param map
	 * @param list
	 */

	public void editPurBill(List<PurBillModel> list, Map<String, String> map, User user) {
		// TODO Auto-generated method stub

		if (list != null && list.size() > 0) {
			// 先删除原来的采购清单信息
			purchaseDao.deletePurBillByPurId(map.get("id").toString());

			for (int i = 0; i < list.size(); i++) {
				PurBillModel purBillModel = list.get(i);
				purBillModel.setSerialNumber(String.valueOf(i + 1));
				purBillModel.setId(UUID.randomUUID().toString().replaceAll("-", ""));// id
				purBillModel.setCreateName(map.get("modifyName").toString());// 创建人姓名
				purBillModel.setPurId(map.get("id").toString());
				purBillModel.setPurNo(map.get("purNo").toString());

				purchaseDao.addPurBill(purBillModel);// 插入采购清单信息
			}
			// 更新清单状态
			purchaseDao.updatePurchaseStatus(map);
		}
	}

	/**
	 * 采购清单信息
	 * @param map
	 * @return
	 */
	public List<Purchase> queryPurInfoList(Map map) {
		// TODO Auto-generated method stub
		return purchaseDao.queryPurInfoList(map);
	}
}
