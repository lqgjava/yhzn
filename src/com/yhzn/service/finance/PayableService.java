package com.yhzn.service.finance;

import java.io.FileNotFoundException;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.common.Attachment;
import com.yhzn.model.finance.PayableDetailsModel;
import com.yhzn.model.finance.PayableModel;
import com.yhzn.model.finance.ReceivableDetailsModel;

public interface PayableService {
	/**
	 * 查询公司账款信息
	 * 
	 * @param bounds
	 * @param receivable
	 * @return
	 */
	List<PayableModel> QueryOweMoney(PageBounds bounds, PayableModel payable);

	/**
	 * 根据id查详情
	 * 
	 * @param bounds
	 * @param id
	 * @return
	 */
	List<PayableDetailsModel> QueryById(PageBounds bounds, String id,String unPaydMoney);

	/**
	 * 新增账款
	 * 
	 * @param remodel
	 */
	void insertFinance(PayableModel remodel);

	/**
	 * 增加详情信息
	 * 
	 * @param details
	 * @param list
	 */
	void insertReceivableDetails(PayableDetailsModel details, List<Attachment> list);

	/**
	 * 编辑详情信息
	 * 
	 * @param receivable
	 * @param list
	 */
	void editReceivableDetails(PayableDetailsModel receivable, List<Attachment> list);

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
	PayableModel queryContractById(String id);

	/**
	 * 跟新信息
	 * 
	 * @param recreModel
	 */
	void updateFinance(PayableModel recreModel);

	void deleteReceivable(String id) throws FileNotFoundException;

	List<PayableDetailsModel> QueryByIdT(String id, String unPaydMoney);

	String queryCompanyByPayableId(String id);

	int queryrecepitMoney(String id);

	int queryunpaidMoney(String id);
}
