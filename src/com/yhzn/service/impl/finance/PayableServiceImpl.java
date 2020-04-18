package com.yhzn.service.impl.finance;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.common.AttachmentDao;
import com.yhzn.dao.finance.PayableDao;
import com.yhzn.model.common.Attachment;
import com.yhzn.model.finance.PayableDetailsModel;
import com.yhzn.model.finance.PayableModel;
import com.yhzn.model.finance.ReceivableDetailsModel;
import com.yhzn.service.common.AttachmentService;
import com.yhzn.service.finance.PayableService;

@Service
public class PayableServiceImpl implements PayableService {

	@Resource
	private PayableDao paiableDao;
	@Resource
	private AttachmentDao attachmentDao;
	@Autowired
	private AttachmentService attachmentService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Override
	public List<PayableModel> QueryOweMoney(PageBounds bounds, PayableModel payable) {
		return paiableDao.QueryALL(bounds, payable);
	}

	@Override
	public List<PayableDetailsModel> QueryById(PageBounds bounds, String id,String unPaydMoney) {
		// 集合数据
				List<PayableDetailsModel> paylist = paiableDao.QueryById(bounds, id,unPaydMoney);
				// 应收尾款帐id
				List<String> idList = paiableDao.queryLastId(id);
				for (PayableDetailsModel pay : paylist) {
					for (String string : idList) {
						if (pay.getId().equals(string)) {
							if (Integer.parseInt(pay.getUnPaydMoney()) == 0) {
								pay.setState("<label>正常</label>");
							} else {
								try {
								Date now = new Date();
								long nowDate = now.getTime();
								long nextDate = sdf.parse(pay.getNextDate()).getTime();
								if(nowDate>nextDate) {
									pay.setState("<label style='color:red'>正常</label>");
								}else{
									pay.setState("<label>正常</label>");
								}
									} catch (ParseException e) {
										
										e.printStackTrace();
									}
								
								
							}
						}else {
							pay.setState("<label>正常</label>");
						}
					}
				}
				return paylist;
	}

	@Override
	public void insertFinance(PayableModel remodel) {
		paiableDao.insertFinance(remodel);
	}

	@Override
	public void insertReceivableDetails(PayableDetailsModel receivable, List<Attachment> lists) {
		receivable.setId(UUID.randomUUID().toString().replaceAll("-", ""));// id
		if(lists.size()>0) {
		for (Attachment list : lists) {
			list.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			list.setParentId(receivable.getId());
			attachmentDao.insertAtt(list);
		}
		}
		paiableDao.insertReceivableDetails(receivable);
		Integer count = paiableDao.queryunpaidMoney(receivable.getFinanceId());
		// 修改公司欠款金额

		paiableDao.updateReceivableMoney(receivable.getFinanceId(), count);

	}

	@Override
	public void editReceivableDetails(PayableDetailsModel receivable, List<Attachment> list) {
		if (list.size() > 0) {
			for (Attachment att : list) {
				att.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				att.setParentId(receivable.getId());
				attachmentDao.insertAtt(att);
			}
		}
		paiableDao.editReceivableDetails(receivable);
		Integer count = paiableDao.queryunpaidMoney(receivable.getFinanceId());
		// 修改公司欠款金额

		paiableDao.updateReceivableMoney(receivable.getFinanceId(), count);
	}

	@Override
	public void deteleReceivableDetails(String[] ids) throws FileNotFoundException {
		for (String string : ids) {
			// 删除附件
			attachmentService.deleteFileByParentId(string);// 删除附件
		}
		paiableDao.deleteReceivableDetails(ids);

	}

	@Override
	public PayableModel queryContractById(String id) {

		return paiableDao.queryContractById(id);
	}

	@Override
	public void updateFinance(PayableModel recreModel) {
		paiableDao.updateFinance(recreModel);

	}

	@Override
	public void deleteReceivable(String id) throws FileNotFoundException {
		// 根据账款id查找
		List<String> detailIds = paiableDao.queryDetailsIds(id);// 详情列表id
		if (detailIds.size() > 0) {
			for (String detailId : detailIds) {
				// 根据详情的id查找附件并删除附件
				attachmentService.deleteFileByParentId(detailId);// 删除附件
				paiableDao.deleteDetailsById(detailId);// 删除详情
			}
		}
		paiableDao.deleteReceivable(id);
	}

	@Override
	public List<PayableDetailsModel> QueryByIdT(String id, String unPaydMoney) {
		
		return paiableDao.QueryByIdT(id,unPaydMoney);
	}

	@Override
	public String queryCompanyByPayableId(String id) {
		return paiableDao.queryCompanyByPayableId(id);
	}

	@Override
	public int queryrecepitMoney(String id) {
		return paiableDao.queryrecepitMoney(id);
	}

	@Override
	public int queryunpaidMoney(String id) {
		return paiableDao.queryunpaidMoney(id);
	}

}
