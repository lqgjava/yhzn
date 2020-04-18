package com.yhzn.service.impl.finance;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.finance.PurchaseDetailsDao;
import com.yhzn.model.finance.PurchaseDetails;
import com.yhzn.model.security.User;
import com.yhzn.service.finance.PurchaseDetailsService;

/**
 * 
 * @author Liany
 *采购合同详情service实现类
 */
@Service
public class PurchaseDetailsServiceImpl implements PurchaseDetailsService {
	
	@Resource
	private PurchaseDetailsDao purchaseDetailsDao;
	/**
	 * 添加一条财务采购合同详情新记录
	 */
	@Override
	public Map<String, Object> addPurchaseDetails(PurchaseDetails purchaseDetails, User user) {
		purchaseDetails.setId(UUID.randomUUID().toString().replace("-", ""));
		purchaseDetails.setCreateName(user.getCreateName());
		purchaseDetails.setCreateDate(new Date());
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			purchaseDetailsDao.insPurchaseDetails(purchaseDetails);
			map.put("status", "200");
			map.put("msg", "保存成功");
		} catch (Exception e) {
			map.put("status", "400");
			map.put("msg", "保存失败");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询采购合同详情列表
	 */
	@Override
	public List<PurchaseDetails> queryPurchaseDetailsList(PageBounds bounds, Map<String, Object> parameter) {
		return purchaseDetailsDao.queryPurchaseDetailsList(bounds, parameter);
	}
	
	/**
	 * 修改采购合同详情信息
	 */
	@Override
	public Map<String, Object> updatePurchaseDetails(PurchaseDetails purchaseDetails, User user) {
		purchaseDetails.setModifyName(user.getModifyName());
		purchaseDetails.setModifyDate(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			purchaseDetailsDao.updatePurchaseDetails(purchaseDetails);
			map.put("status", "200");
			map.put("msg","修改成功");
		} catch (Exception e) {
			map.put("status", "400");  
			map.put("msg","保存失败" );
		}
		return map;
	}
	
	/**
	 * 根据id查询采购合同详情
	 */
	@Override
	public PurchaseDetails selPurchaseDetailsById(String id) {
		PurchaseDetails purchaseDetails = purchaseDetailsDao.selPurchaseDetailsById(id);
		return purchaseDetails;
	}
	
	/**
	 * 根据id删除采购合同详情
	 */
	@Override
	public void delPurchaseDetailsById(String id, User user) {
		purchaseDetailsDao.delPurchaseDetailsById(id);
	}

}
