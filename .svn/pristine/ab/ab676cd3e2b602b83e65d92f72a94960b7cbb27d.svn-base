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
import com.yhzn.dao.finance.ReceivableDao;
import com.yhzn.model.common.Attachment;
import com.yhzn.model.finance.ReceivableDetailsModel;
import com.yhzn.model.finance.ReceivableModel;
import com.yhzn.service.common.AttachmentService;
import com.yhzn.service.finance.ReceivableService;

@Service
public class ReceivableServiceImpl implements ReceivableService {

	@Resource
	private ReceivableDao receivableDao;
	@Resource
	private AttachmentDao attachmentDao;
	@Autowired
	private AttachmentService attachmentService;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Override
	public List<ReceivableModel> QueryOweMoney(PageBounds bounds, ReceivableModel receivableModel) {
		return receivableDao.QueryALL(bounds, receivableModel);
	}

	@Override
	public List<ReceivableDetailsModel> QueryById(PageBounds bounds, String id, String unpaidMoney) {
		// 集合数据
		List<ReceivableDetailsModel> recelist = receivableDao.QueryById(bounds, id, unpaidMoney);
		// 应收尾款帐id
		List<String> idList = receivableDao.queryLastId(id);
		for (ReceivableDetailsModel rece : recelist) {
			for (String string : idList) {
				if (rece.getId().equals(string)) {
					if (Integer.parseInt(rece.getUnpaidMoney()) == 0) {
						rece.setState("<label>正常</label>");
					} else {
						try {
							Date now = new Date();
							long nowDate = now.getTime();
							if (rece.getNextDate() == null) {
								rece.setState("<label>正常</label>");
							} else {
								long nextDate = sdf.parse(rece.getNextDate()).getTime();
								if (nowDate > nextDate) {
									rece.setState("<label style='color:red'>正常</label>");
								} else {
									rece.setState("<label>正常</label>");
								}
							}
						} catch (ParseException e) {

							e.printStackTrace();
						}

					}
				} else {
					rece.setState("<label>正常</label>");
				}
			}
		}
		return recelist;
	}

	@Override
	public void insertFinance(ReceivableModel remodel) {
		receivableDao.insertFinance(remodel);
	}

	@Override
	public void insertReceivableDetails(ReceivableDetailsModel receivable, List<Attachment> lists) {
		receivable.setId(UUID.randomUUID().toString().replaceAll("-", ""));// id
		if (lists.size() > 0) {
			for (Attachment list : lists) {
				list.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				list.setParentId(receivable.getId());
				attachmentDao.insertAtt(list);
			}
		}
		receivableDao.insertReceivableDetails(receivable);
		// 统计该公司欠款金额
		Integer count = receivableDao.queryunpaidMoney(receivable.getFinanceId());
		// 修改公司欠款金额

		receivableDao.updateReceivableMoney(receivable.getFinanceId(), count);

	}

	@Override
	public void editReceivableDetails(ReceivableDetailsModel receivable, List<Attachment> list) {
		if (list.size() > 0) {
			for (Attachment att : list) {
				att.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				att.setParentId(receivable.getId());
				attachmentDao.insertAtt(att);
			}
		}
		receivableDao.editReceivableDetails(receivable);
		// 统计该公司欠款金额
		Integer count = receivableDao.queryunpaidMoney(receivable.getFinanceId());
		// 修改公司欠款金额

		receivableDao.updateReceivableMoney(receivable.getFinanceId(), count);
	}

	@Override
	public void deteleReceivableDetails(String[] ids) throws FileNotFoundException {
		receivableDao.deleteReceivableDetails(ids);
		for (String string : ids) {
			// 删除附件
			attachmentService.deleteFileByParentId(string);// 删除附件
		}

	}

	@Override
	public ReceivableModel queryContractById(String id) {

		return receivableDao.queryContractById(id);
	}

	@Override
	public void updateFinance(ReceivableModel recreModel) {
		receivableDao.updateFinance(recreModel);

	}

	@Override
	public void deleteReceivable(String id) {
		// 根据账款id查找
		List<String> detailIds = receivableDao.queryDetailsIds(id);// 详情列表id
		if (detailIds.size() > 0) {
			for (String detailId : detailIds) {
				// 根据详情的id查找附件并删除附件
				attachmentService.deleteFileByParentId(detailId);// 删除附件
				receivableDao.deleteDetailsById(detailId);// 删除详情
			}

		}
		receivableDao.deleteReceivable(id);
	}

	@Override
	public List<ReceivableDetailsModel> QueryByIdT(String id, String unpaidMoney) {
		return receivableDao.QueryByIdT(id, unpaidMoney);
	}

	@Override
	public String queryCompanyByFinanceId(String id) {

		return receivableDao.queryCompanyByFinanceId(id);
	}

	@Override
	public int queryrecepitMoney(String id) {

		return receivableDao.queryrecepitMoney(id);
	}

	@Override
	public int queryunpaidMoney(String id) {
		// TODO Auto-generated method stub
		return receivableDao.queryunpaidMoney(id);
	}

}
