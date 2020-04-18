package com.yhzn.service.impl.customer;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.customer.CustomerDao;
import com.yhzn.dao.customer.CustomerProjDao;
import com.yhzn.model.customer.CustomerProj;
import com.yhzn.model.customer.PriceBill;
import com.yhzn.model.security.User;
import com.yhzn.service.common.SysSequenceService;
import com.yhzn.service.customer.CustomerProjService;

/**
 * 客户项目信息实现类
 * @author liany
 *
 */
@Service
public class CustomerProjServiceImpl implements CustomerProjService{

	//客户项目信息dao
	@Resource
	private CustomerProjDao customerProjDao;
	@Autowired
	private SysSequenceService sysSequenceService;
	
	/**
	 * 查询客户信息项目列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<CustomerProj> queryCustomerProjList(PageBounds bounds,
			Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		return customerProjDao.queryCustomerProjList(bounds, parameter);
	}

	/**
	 * 删除客户项目信息
	 * @param id
	 */
	@Override
	public void deleteCustomerProjById(String id) {
		// TODO Auto-generated method stub
		customerProjDao.deleteCustomerProjById(id);
	}

	/**
	 * 新增客户项目信息
	 * @param customerProj
	 * @param request
	 * @return
	 */
	@Override
	public void insertCustomerProj(CustomerProj customerProj, User user) {
		// TODO Auto-generated method stub
		customerProj.setProjectNo("X"+sysSequenceService.getNextNo("CUSTOMER_PROJ", user));
		customerProj.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
		customerProj.setCreateName(user.getTrueName());//创建人姓名
		
		customerProjDao.insertCustomerProj(customerProj);
	}

	/**
	 * 修改客户项目信息
	 * @param customerProj
	 * @param request
	 * @return
	 */
	@Override
	public void editCustomerProj(CustomerProj customerProj, User user) {
		// TODO Auto-generated method stub
		customerProj.setModifyName(user.getTrueName());//修改人姓名
				
		customerProjDao.editCustomerProj(customerProj);
	}

	/**
	 * 根据ID查询客户项目信息
	 * @param id
	 */
	@Override
	public CustomerProj queryCustomerProjById(String id) {
		// TODO Auto-generated method stub
		return customerProjDao.queryCustomerProjById(id);
	}

	/**
	 * 新增项目报价清单信息
	 * @param user
	 * @param list
	 */
	public void addPriceBill(List<PriceBill> list,Map map){
		if(list!=null && list.size()>0){
			//先删除原来的产品报价信息
			customerProjDao.deletePriceBillByProjId(map.get("id").toString());
			
			for(PriceBill priceBill : list){
				priceBill.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
				priceBill.setCreateName(map.get("modifyName").toString());//创建人姓名
				
				customerProjDao.addPriceBill(priceBill);//插入产品清单信息
	        }
			//更新项目清单状态 1-未提交，2-已提交
			customerProjDao.updateCustomerProjStatus(map);
		}
	}


	/**
	 * 查询清单产品详细信息
	 * @param id
	 * @return
	 */
	@Override
	public List<PriceBill> queryPriceBillList(String id) {
		// TODO Auto-generated method stub
		return customerProjDao.queryPriceBillList(id);
	}
}
