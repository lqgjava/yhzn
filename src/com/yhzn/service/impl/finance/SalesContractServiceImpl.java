package com.yhzn.service.impl.finance;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.common.util.CoreConst;
import com.yhzn.dao.finance.SalesContractDao;
import com.yhzn.model.finance.SalesContract;
import com.yhzn.model.security.User;
import com.yhzn.service.finance.SalesContractService;

/**
 * 销售合同service实现类
 * @author Liany
 *
 */
@Service
public class SalesContractServiceImpl implements SalesContractService{
	@Resource
	private SalesContractDao financialSalesContractDao; 
	
	/**
	 * 添加销售合同的service实现类的方法
	 */
	@Override
	public Map<String, Object> addFinancialSalesContract(SalesContract financialSalesContract, User user) {
    //	设置用户id
		financialSalesContract.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		financialSalesContract.setCreateName(user.getCreateName());
		financialSalesContract.setCreateDate(new Date());
		financialSalesContract.setDeleteFlag(CoreConst.NO_DEL);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			financialSalesContractDao.addFinancialSalesContract(financialSalesContract);
			map.put("status", "200");
			map.put("msg", "保存成功");
		} catch (Exception e) {
			map.put("status", "400");
			map.put("msg", "保存失败");
		}
		return map;
	}
	
	/**
	 * 查询用户列表的service实现类的方法
	 */
	@Override
	public List<SalesContract> queryFinancialSalesContractList(PageBounds bounds,
			Map<String, Object> parameter) {
		return financialSalesContractDao.queryFinancialSalesContractList(bounds, parameter);
	}
	
	/**
	 * 修改用户的service实现类的方法
	 */
	@Override
	public Map<String, Object> updateFinancialSalesContract(SalesContract financialSalesContract, User user) {
		financialSalesContract.setModifyName(user.getModifyName());
		financialSalesContract.setModifyDate(user.getModifyDate());
		financialSalesContract.setDeleteFlag(CoreConst.NO_DEL);
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			financialSalesContractDao.editFinancialSalesContract(financialSalesContract);
			map.put("status", "200");
			map.put("msg", "修改成功");
		} catch (Exception e) {
			map.put("status", "400");
			map.put("msg", "修改失败");
		}
		return map;
	}
	
	/**
	 * 根据用户的id查询用户的service实现类的方法
	 */
	@Override
	public SalesContract selFinancialSalesContractById(String id) {
		SalesContract financialSalesContract = financialSalesContractDao.selFinancialSalesContractById(id);
		return financialSalesContract;
	}
	
	/**
	 * 根据id删除用户的service实现类的方法
	 */
	@Override
	public void delFinancialSalesContractById(String id, User user) {
		financialSalesContractDao.delFinancialSalesContractById(id);
	}

}
