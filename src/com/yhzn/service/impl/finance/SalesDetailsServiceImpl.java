package com.yhzn.service.impl.finance;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.finance.SalesDetailsDao;
import com.yhzn.model.finance.SalesDetails;
import com.yhzn.model.security.User;
import com.yhzn.service.finance.SalesDetailsService;

/**
 * 
 * @author Liany
 * 销售合同详情service实现类
 */
@Service
public class SalesDetailsServiceImpl implements SalesDetailsService {
	
	@Resource
	private SalesDetailsDao salesDetailsDao;
	
	@Override
	public Map<String, Object> addSalesDetails(SalesDetails salesDetails, User user) {
		salesDetails.setId(UUID.randomUUID().toString().replace("-", ""));
		salesDetails.setAcceptId(UUID.randomUUID().toString().replace("-", ""));
		salesDetails.setCreateName(user.getCreateName());
		salesDetails.setCreateDate(new Date());
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			salesDetailsDao.insSalesDetails(salesDetails);
			map.put("status", "200");
			map.put("msg", "保存成功");
		} catch (Exception e) {
			map.put("status", "400");
			map.put("msg", "保存失败");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public List<SalesDetails> querySalesDetailsList(PageBounds bounds, Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return salesDetailsDao.querySalesDetailsList(bounds, parameter);
	}

	@Override
	public Map<String, Object> updateSalesDetails(SalesDetails salesDetails, User user) {
		salesDetails.setModifyName(user.getModifyName());
		salesDetails.setModifyDate(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			salesDetailsDao.updateSalesDetails(salesDetails);
			map.put("status", "200");
			map.put("msg","修改成功");
		} catch (Exception e) {
			map.put("status", "400");  
			map.put("msg","保存失败" );
		}
		return map;
	}

	@Override
	public SalesDetails selSalesDetailsById(String id) {
		SalesDetails salesDetails = salesDetailsDao.selSalesDetailsById(id);
		return salesDetails;
	}

	@Override
	public void delSalesDetailsById(String id, User user) {
		salesDetailsDao.delSalesDetailsById(id);
		
	}
	
	
}
