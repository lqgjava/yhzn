package com.yhzn.service.finance;
import org.springframework.web.multipart.MultipartFile;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.finance.PurchaseContract;
import com.yhzn.model.person.Person;
import com.yhzn.model.security.User;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
/**
 * 财务采购合同服务接口层
 * @author Liany
 */
public interface PurchaseContractService{
	/**
	* 添加一条财务采购合同新记录
	* @param aFinancePurchaseContract 添加的财务采购合同对象
	*/
	Map<String,Object> addFinancePurchaseContract(PurchaseContract aFinancePurchaseContract,User user);
	
	List<PurchaseContract> queryFinancePurchaseContractList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 修改采购合同信息
	 * @param financePurchaseContract
	 * @param user
	 */
	Map<String,Object> updateFinancePurchaseContract(PurchaseContract financePurchaseContract,User user);
	
	/**
	 * 根据id查询采购合同详情
	 * @param id
	 * @return
	 */
	PurchaseContract selFinancePurchaseContractById(String id);
	
	/**
	 * 根据id删除采购合同
	 * @param id
	 * @param user
	 */
	void delFinancePurchaseContractById(String id,User user);
}