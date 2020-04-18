package com.yhzn.service.impl.finance;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.common.util.CoreConst;
import com.yhzn.dao.finance.PurchaseContractDao;
import com.yhzn.model.finance.PurchaseContract;
import com.yhzn.model.security.User;
import com.yhzn.service.finance.PurchaseContractService;

@Service
public class PurchaseContractServiceImpl implements PurchaseContractService {
	
	@Resource
	private PurchaseContractDao financePurchaseContractDao; 
	
	
	@Override
	public Map<String,Object> addFinancePurchaseContract(PurchaseContract financePurchaseContract,User user) {
		financePurchaseContract.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		financePurchaseContract.setCreateName(user.getUserName());
		financePurchaseContract.setCreateDate(new Date());
		financePurchaseContract.setDeleteFlag(CoreConst.NO_DEL);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			financePurchaseContractDao.insertFinancePurchaseContract(financePurchaseContract);
			  map .put("status", "200");  
			  map.put("msg","保存成功" );
		} catch (Exception e) {
			map.put("status", "400");  
			map.put("msg","保存失败" );
			e.printStackTrace();
		}
		return map; 
		
	}
	/**
	 * 查询采购合同的列表
	 */
	@Override
	public List<PurchaseContract> queryFinancePurchaseContractList(PageBounds bounds,
			Map<String, Object> parameter) {
		
		return financePurchaseContractDao.queryFinancePurchaseContractList(bounds, parameter);
	}
	
	/**
	 * 修改采购合同信息
	 */
	@Override
	public Map<String,Object> updateFinancePurchaseContract(PurchaseContract financePurchaseContract, User user) {
		financePurchaseContract.setModifyName(user.getModifyName());
		financePurchaseContract.setModifyDate(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			financePurchaseContractDao.updateFinancePurchaseContract(financePurchaseContract);
			map.put("status", "200");
			map.put("msg","修改成功");
		} catch (Exception e) {
			map.put("status", "400");  
			map.put("msg","保存失败" );
		}
		return map;
	}
	
	/**
	 * 根据id查询采购合同
	 */
	@Override
	public PurchaseContract selFinancePurchaseContractById(String id) {
		PurchaseContract financePurchaseContract = financePurchaseContractDao.selFinancePurchaseContractById(id);
		return financePurchaseContract;
	}
	
	/**
	 * 根据id删除采购合同
	 */
	@Override
	public void delFinancePurchaseContractById(String id, User user) {
		financePurchaseContractDao.delFinancePurchaseContractById(id);		
	}
	
	
	
}

