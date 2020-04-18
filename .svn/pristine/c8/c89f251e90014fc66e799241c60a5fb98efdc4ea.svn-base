package com.yhzn.common.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yhzn.common.util.ExportExcel;
import com.yhzn.model.finance.PayableDetailsModel;
import com.yhzn.model.finance.ReceivableDetails;
import com.yhzn.model.finance.ReceivableDetailsModel;
import com.yhzn.service.finance.PayableService;
import com.yhzn.service.finance.ReceivableService;

@Controller
@RequestMapping("/exl")
public class ExlController extends BaseController {

	@Autowired
	private ReceivableService receivableService;
	@Autowired
	private PayableService payableService;
/**
 * 单位欠款详情导出exl
 * @param id
 * @param unpaidMoney
 * @param response
 */
	@RequestMapping("/recevibleExl")
	public void createRecevibleDetailsExcel(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "unpaidMoney", required = false) String unpaidMoney, HttpServletResponse response) {
		List<ReceivableDetailsModel> excellist = receivableService.QueryByIdT(id, unpaidMoney);// 根据条件在数据库拿值
		// 根据id获取公司名称
		String companyName = receivableService.queryCompanyByFinanceId(id);
		List<ReceivableDetails> excellists = new ArrayList<ReceivableDetails>();
		for (int i = 0; i < excellist.size(); i++) {
			ReceivableDetails receivableDetails = new ReceivableDetails();
			Map<String, Object> map = object2Map(excellist.get(i));
			Set<String> keys = map.keySet();

			for (String key : keys) {
				if (key.equals("contractNo")) {
					receivableDetails.setContractNo(map.get(key).toString());
				}
				if (key.equals("detailMoney")) {
					receivableDetails.setDetailMoney(map.get(key).toString());
				}

				if (key.equals("recepitMoney")) {
					receivableDetails.setRecepitMoney(map.get(key).toString());
				}
				if (key.equals("unpaidMoney")) {
					receivableDetails.setUnpaidMoney(map.get(key).toString());
				}
				if (key.equals("insertDate")) {
					receivableDetails.setInsertDate(map.get(key).toString());
				}

			}

			excellists.add(receivableDetails);
		}
		String[] Title = { "项目合同编号", "应收款/元", "实际收款/元", "结存/元", "时间" };
		ExportExcel.exportExcel(companyName + "欠款详情.xls", Title, excellists, response, companyName + "欠款详情表", 1);

	}
	/**
	 * 公司付款详情导出exl
	 * @param id
	 * @param unpaidMoney
	 * @param response
	 */
		@RequestMapping("/payableExl")
		public void createpayableDetailsExcel(@RequestParam(value = "id", required = true) String id,
				@RequestParam(value = "unpaidMoney", required = false) String unPaydMoney, HttpServletResponse response) {
			List<PayableDetailsModel> excellist = payableService.QueryByIdT(id, unPaydMoney);// 根据条件在数据库拿值
			// 根据id获取公司名称
			String companyName = payableService.queryCompanyByPayableId(id);
			List<ReceivableDetails> excellists = new ArrayList<ReceivableDetails>();
			for (int i = 0; i < excellist.size(); i++) {
				ReceivableDetails receivableDetails = new ReceivableDetails();
				Map<String, Object> map = object2Map(excellist.get(i));
				Set<String> keys = map.keySet();

				for (String key : keys) {
					if (key.equals("contractNo")) {
						receivableDetails.setContractNo(map.get(key).toString());
					}
					if (key.equals("detailMoney")) {
						receivableDetails.setDetailMoney(map.get(key).toString());
					}

					if (key.equals("recepitMoney")) {
						receivableDetails.setRecepitMoney(map.get(key).toString());
					}
					if (key.equals("unpaidMoney")) {
						receivableDetails.setUnpaidMoney(map.get(key).toString());
					}
					if (key.equals("insertDate")) {
						receivableDetails.setInsertDate(map.get(key).toString());
					}

				}

				excellists.add(receivableDetails);
			}
			String[] Title = { "项目合同编号", "应付款/元", "实际付款/元", "结存/元", "时间" };
			ExportExcel.exportExcel(companyName + "付款详情.xls", Title, excellists, response, companyName + "付款详情表", 1);

		}
	/**
	 * 将实体类对象转化成map对象
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> object2Map(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (obj == null) {
			return map;
		}
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
