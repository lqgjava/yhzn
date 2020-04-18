package com.yhzn.service.finance;

import java.io.FileNotFoundException;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.common.Attachment;
import com.yhzn.model.finance.ReceivableDetailsModel;
import com.yhzn.model.finance.ReceivableModel;

public interface ReceivableService {
	/**
	 * 查询公司账款信息
	 * 
	 * @param bounds
	 * @param receivable
	 * @return
	 */
	List<ReceivableModel> QueryOweMoney(PageBounds bounds, ReceivableModel receivable);

	/**
	 * 根据id查详情
	 * 
	 * @param bounds
	 * @param id
	 * @return
	 */
	List<ReceivableDetailsModel> QueryById(PageBounds bounds, String id,String unpaidMoney);

	/**
	 * 新增账款
	 * 
	 * @param remodel
	 */
	void insertFinance(ReceivableModel remodel);

	/**
	 * 增加详情信息
	 * 
	 * @param details
	 * @param list
	 */
	void insertReceivableDetails(ReceivableDetailsModel details, List<Attachment> list);

	/**
	 * 编辑详情信息
	 * 
	 * @param receivable
	 * @param list
	 */
	void editReceivableDetails(ReceivableDetailsModel receivable, List<Attachment> list);

	/**
	 * 删除详情信息
	 * 
	 * @param ids
	 */
	void deteleReceivableDetails(String[] ids) throws FileNotFoundException;

	/**
	 * 根据id查询账款信息
	 * 
	 * @param id
	 * @return
	 */
	ReceivableModel queryContractById(String id);

	/**
	 * 跟新信息
	 * 
	 * @param recreModel
	 */
	void updateFinance(ReceivableModel recreModel);

	void deleteReceivable(String id) throws FileNotFoundException;

	List<ReceivableDetailsModel> QueryByIdT(String id, String unpaidMoney);

	String queryCompanyByFinanceId(String id);

	int queryrecepitMoney(String id);

	int queryunpaidMoney(String id);
}
