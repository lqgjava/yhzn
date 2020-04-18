package com.yhzn.dao.finance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.finance.ReceivableDetailsModel;
import com.yhzn.model.finance.ReceivableModel;

public interface ReceivableDao {
	/**
	 * 根据合同分类查询最后收帐时间
	 * 
	 * @return
	 */
	List<ReceivableModel> QueryALL(PageBounds bounds, ReceivableModel rece);

	/**
	 * 公司欠款类表
	 * 
	 * @param bounds
	 * @param company
	 * @return
	 */
	List<ReceivableDetailsModel> QueryById(PageBounds bounds, @Param("id") String id,@Param("unpaidMoney")String unpaidMoney);

	/**
	 * 新增
	 */
	void insertFinance(ReceivableModel remodel);

	/**
	 * 编辑收款详情
	 * 
	 * @param receivable
	 */
	void editReceivableDetails(ReceivableDetailsModel receivable);

	/**
	 * 删除收款详情
	 * 
	 * @param ids
	 */
	void deleteReceivableDetails(@Param(value = "ids") String[] ids);

	/**
	 * 新增详情
	 * 
	 * @param receivable
	 */
	void insertReceivableDetails(ReceivableDetailsModel receivable);

	/**
	 * 根据id查询账款信息
	 * 
	 * @param id
	 * @return
	 */
	ReceivableModel queryContractById(@Param("id") String id);

	/**
	 * 修改账款信息
	 * 
	 * @param recreModel
	 */
	void updateFinance(ReceivableModel recreModel);

	/**
	 * 根parentId查询详情信息
	 * 
	 * @param id
	 * @return
	 */
	List<String> queryDetailsIds(@Param("detailId") String detailId);

	/**
	 * 根据id删除详情信息
	 * 
	 * @param detailId
	 */
	void deleteDetailsById(@Param("detailId") String detailId);

	/**
	 * 删除账款信息
	 * 
	 * @param id
	 */
	void deleteReceivable(String id);

	void updateReceivableMoney(@Param("financeId") String financeId, @Param("count") Integer count);

	


	List<ReceivableDetailsModel> QueryByIdT( @Param("id") String id,@Param("unpaidMoney")String unpaidMoney);

	String queryCompanyByFinanceId(@Param("financeId")String financeId);

	int queryrecepitMoney(@Param("financeId")String financeId);

	int queryunpaidMoney(@Param("financeId")String financeId);

	List<String> queryLastId(@Param("id")String id);

}
